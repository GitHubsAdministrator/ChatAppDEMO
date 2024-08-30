package top.certstone;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class UserServiceThread extends Thread {
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

    public void sendMsg(Massage msg) {
        try {
            oos.writeObject(msg);
            ConsoleLog.sendMsg(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Massage receiveMsg() {
        try {
            Massage msg = (Massage) ois.readObject();
            ConsoleLog.receiveMsg(msg);
            return msg;
        } catch (Exception e) {
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
//                    chatGUI.addFile(msg);
                break;
            case MassageType.ERROR:
                new WarnMassage(chatGUI, msg.getContent());
                loop = false;
                break;
//            case MassageType.SUCCESS:
//
//                break;
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
