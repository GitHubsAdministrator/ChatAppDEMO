package top.certstone;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

import static top.certstone.chatAppServer.clients;

public class chatAppServer {

    public static int port = 61000; // Default port
    public static String key = null; // Default key
    public static HashMap<ServerThread,User> clients = new HashMap(); // Create a vector to store clients
    public static Vector<User> users = new Vector<>(); // Create a vector to store users

    public static void main(String[] args) {
        handleParams(args);

        try { // Create a server socket
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            while (true) {
                ServerThread serverThread = new ServerThread(server.accept());
                serverThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeClient(ServerThread serverThread) {
        clients.remove(serverThread);
    }


    public static void handleParams(String[] args){
        // 获取端口参数-p (or --port)
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p") || args[i].equals("--port")) {
                port = Integer.parseInt(args[i + 1]);
            }
        }

        //获取密钥参数-k(or --key)
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-k") || args[i].equals("--key")) {
                key = args[i + 1];
            }
        }
    }
}

// Define the ServerThread class
class ServerThread extends Thread {
    private Socket socket;
    private User user;
    private Boolean loop = true;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        this.ois = new ObjectInputStream(socket.getInputStream());
        this.oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public void sendMsg(Massage msg) {
        try {
            oos.writeObject(msg);
            ServerLog.sendMsg(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcast(Massage msg) {
        for (ServerThread serverThread : clients.keySet()) {
            serverThread.sendMsg(msg);
        }
        ServerLog.broadcast(msg);
    }

    private void checkInfo() {
        // 检查：
        // 1、Key是否正确
        // 2、用户是否已经存在
        // 若检查通过，return；若检查不通过，发送错误信息并关闭连接（线程）

        try {
            Massage msg = (Massage) ois.readObject();
            if (msg.getType() != MassageType.LOGIN) {
                Massage retMsg = new Massage(MassageType.ERROR, "Please Connect first", null);
                sendMsg(retMsg);
                socket.close();
                loop = false;
                return;
            }
            if (!msg.getContent().equals(chatAppServer.key)){
                Massage retMsg = new Massage(MassageType.ERROR, "Key error", null);
                sendMsg(retMsg);
                socket.close();
                loop = false;
                return;
            }
            for (User user : chatAppServer.users) {
                if (user.getName().equals(msg.getSender().getName())) {
                    Massage retMsg = new Massage(MassageType.ERROR, "User already exists", null);
                    sendMsg(retMsg);
                    socket.close();
                    loop = false;
                    return;
                }
            }
            user = msg.getSender();
            chatAppServer.users.add(user);
            Massage retMsg = new Massage(MassageType.TEXT, "[INFO]\""+user.getName()+"\" came to the chatroom", null);
            broadcast(retMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleMsg(Massage msg) {
        switch (msg.getType()) {
            case MassageType.TEXT:
                if (msg.getReceiver() == null) {
                    broadcast(msg);
                } else {
                    for (ServerThread serverThread : clients.keySet()) {
                        if (serverThread.user.getName().equals(msg.getReceiver().getName())) {
                            serverThread.sendMsg(msg);
                            return;
                        }
                    }
                    Massage retMsg = new Massage(MassageType.ERROR, "User not found", null);
                    sendMsg(retMsg);
                }
                break;
            case MassageType.EXIT:

                break;
            case MassageType.FILE:

                break;
            case MassageType.ERROR:

                break;
            case MassageType.SUCCESS:

                break;
            default:
                System.out.println("Unknown massage type");
        }
    }

    @Override
    public void run() {
        // Handle client connection
        try {
            checkInfo();
            clients.put(this, user);
            while (loop) {
                // Receive message from the client
                Massage msg = (Massage) ois.readObject();
                handleMsg(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}