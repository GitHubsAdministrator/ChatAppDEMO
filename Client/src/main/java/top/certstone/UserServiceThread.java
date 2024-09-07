package top.certstone;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

/**
 * @author CertStone
 * @version 1.0
 * This class is used to handle the communication between the client and the server
 * It is a thread that runs in the background to receive and send messages
 * It also handles the login process and the message handling process
 * 用户服务线程，用于处理客户端与服务器之间的通信
 * 该线程后台运行，用于接收和发送消息
 * 同时处理登录过程和消息处理过程
 */

public class UserServiceThread extends Thread {

    /**
     * socket: 用于与服务器通信的套接字
     * user: 当前用户
     * ois: 用于接收消息的对象输入流
     * oos: 用于发送消息的对象输出流
     * chatGUI: 主聊天界面
     * chattingWindows: 已私聊窗口列表
     * key: 登录密钥
     * loop: 用于控制线程循环
     */
    private Socket socket;
    private static User user;
    public ObjectInputStream ois ;
    public ObjectOutputStream oos ;
    public ChatGUI chatGUI;
    public Vector<PrivateChatGUI> chattingWindows = new Vector<>();
    private String key = null;
    public static Boolean loop = true;

    public UserServiceThread(Socket socket, User user, String key) throws IOException {
        this.socket = socket;
        this.user = user;
        this.key = key;
        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush(); // 确保 ObjectOutputStream 已经完全初始化
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public User getUser() {
        return user;
    }

    public void sendMsg(Massage msg) { // 向输出流中写入消息并打印日志
        try {
            oos.writeObject(msg);
            ConsoleLog.sendMsg(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Massage receiveMsg() { // 从输入流中读取消息并打印日志
        try {
            Massage msg = (Massage) ois.readObject();
            ConsoleLog.receiveMsg(msg);
            return msg;
        } catch (Exception e) { // 若出现异常，打印异常信息并关闭连接
            e.printStackTrace();
            new WarnMassage(chatGUI, "Connection Lost");
            loop = false;
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }
    }

    // 检查登录信息并初始化聊天界面
    private void loginCheck(String key) {
        Massage msg = new Massage(MassageType.LOGIN, key, user);
        sendMsg(msg);
        Massage retMsg = receiveMsg();
        if (retMsg.getType() == MassageType.ERROR) {
            new WarnMassage(chatGUI, retMsg.getContent());
            loop = false;
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(
                    new LoginGUI()
            );
            return;
        }
        chatGUI = new ChatGUI(this,user);
    }

    // 处理消息
    private void handleMsg(Massage msg) {
        switch (msg.getType()) {
            case MassageType.EXIT:
                new WarnMassage(chatGUI, msg.getContent());
                try {
                    socket.close();
                    loop = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case MassageType.TEXT:
                if (!msg.isPrivate) {
                    chatGUI.addMessage(msg);
                } else {
                    for (PrivateChatGUI privateChatGUI : chattingWindows) {
                        if (privateChatGUI.targetUser.getName().equals(msg.getSender().getName())) {
                            privateChatGUI.setVisible(true);
                            privateChatGUI.addMessage(msg);
                            return;
                        }
                    }
                    startChatWith(msg.getSender()).addMessage(msg);
                }
                break;
            case MassageType.FILE:
                //TODO: 文件传输
                //chatGUI.addFile(msg);
                break;
            case MassageType.ERROR:
                new WarnMassage(chatGUI, msg.getContent());
                loop = false;
                break;
            case MassageType.USER_LIST:
                chatGUI.updateUserList(msg.getUsers());
                break;
            case MassageType.ROOM_NAME:
                chatGUI.setRoomName(msg.getContent());
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        loginCheck(key);
        while (loop) {
            Massage msg = receiveMsg();
            if (msg == null) {
                continue;
            }
            handleMsg(msg);
        }
    }

    // 启动一个与选定用户的私聊窗口
    public PrivateChatGUI startChatWith(User selectedUser) {
        // 当选定用户不在chattingWindows中时，创建一个新的私聊窗口
        // When the selected user is not in chattingWindows, create a new private chat window
        for (PrivateChatGUI privateChatGUI : chattingWindows) {
            if (privateChatGUI.targetUser.getName().equals(selectedUser.getName())) {
                return privateChatGUI;
            }
        }
        PrivateChatGUI privateChatGUI = new PrivateChatGUI(this, selectedUser);
        chattingWindows.add(privateChatGUI);
        return privateChatGUI;
    }


}
