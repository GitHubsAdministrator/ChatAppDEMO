package top.certstone;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

import static top.certstone.chatAppServer.clients;

public class chatAppServer {

    public static int port = 61000; // Default port
    public static String key = null; // Default key
    public static HashMap<ServerThread,User> clients = new HashMap<>(); // Create a vector to store clients
    public static Vector<User> users = new Vector<>(); // Create a vector to store users

    public static void main(String[] args) {
        handleParams(args);

        try { // Create a server socket
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            // Add shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("Shutting down server...");
                    server.close();
                    for (ServerThread client : clients.keySet()) {
                        client.getSocket().close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));

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
        users.remove(serverThread.getUser());
    }


    public static void handleParams(String[] args){
        // 获取端口参数-p (or --port)
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p") || args[i].equals("--port")) {
                port = Integer.parseInt(args[i + 1]);
            }
        }

        // 获取密钥参数-k(or --key)
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-k") || args[i].equals("--key")) {
                key = args[i + 1];
            }
        }

        // 获取-h/--help参数
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-h") || args[i].equals("--help")) {
                System.out.println("Usage: java -jar chatAppServer.jar [options]");
                System.out.println("Options:");
                System.out.println("  -h, --help         Show this help message and exit");
                System.out.println("  -p, --port <port>  Set the port of the server (default 61000)");
                System.out.println("  -k, --key <key>    Set the key of the server (default null)");
                System.exit(0);
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

    public ServerThread(@NotNull Socket socket) throws IOException {
        this.socket = socket;
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

    public void broadcast(Massage msg) {
        for (ServerThread serverThread : clients.keySet()) {
            try {
                serverThread.oos.writeObject(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ConsoleLog.broadcast(msg);
    }

    private void checkInfo() {
        // 检查：
        // 1、Key是否正确
        // 2、用户是否已经存在
        // 若检查通过，return；若检查不通过，发送错误信息并关闭连接（线程）

        try {
            Massage msg = (Massage) ois.readObject();
            if (msg.getType() != MassageType.LOGIN) {
                Massage retMsg = new Massage(MassageType.ERROR, "Please Connect first", null, msg.getSender());
                sendMsg(retMsg);
                socket.close();
                loop = false;
                return;
            }
            if (!Objects.equals(msg.getContent(), chatAppServer.key)){
                Massage retMsg = new Massage(MassageType.ERROR, "Key error", null, msg.getSender());
                sendMsg(retMsg);
                socket.close();
                loop = false;
                return;
            }
            for (User user : chatAppServer.users) {
                if (user.getName().equals(msg.getSender().getName())) {
                    Massage retMsg = new Massage(MassageType.ERROR, "User already exists", null, msg.getSender());
                    sendMsg(retMsg);
                    socket.close();
                    loop = false;
                    return;
                }
            }
            user = msg.getSender();
            clients.put(this, user);
            chatAppServer.users.add(user);
            Massage retMsg = new Massage(MassageType.TEXT, "[INFO]\""+ConsoleLog.getSenderName(msg)+"\" came to the chatroom (ip "+socket.getInetAddress() +")", null);
            broadcast(retMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleMsg(@NotNull Massage msg) {
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
                Massage retMsg = new Massage(MassageType.TEXT, "[INFO]\""+user.getName()+"\" left the chatroom", null);
                broadcast(retMsg);
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                chatAppServer.removeClient(this);
                loop = false;
                break;
            case MassageType.FILE:
                //TODO: 文件传输
                break;
            case MassageType.USER_LIST:
                // 返回当前的用户列表(Vector<User>)
                Vector<User> users = new Vector<>();
                for (User user : chatAppServer.users) {
                    users.add(user);
                }
                sendMsg(new Massage(MassageType.USER_LIST, null, null, msg.getSender(), users));
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

            while (loop) {
                // Receive message from the client
                Massage msg = (Massage) ois.readObject();
                handleMsg(msg);
            }
        }
        catch (SocketException e) {
            clients.remove(this);
            chatAppServer.users.remove(user);
//            System.out.println("[INFO]\""+user.getName()+"("+user.getUUID().substring(user.getUUID().length()-ConsoleLog.UUID_LEN)+")"+"\" disconnected");
            Massage retMsg = new Massage(MassageType.TEXT, "[INFO]\""+user.getName()+"("+user.getUUID().substring(user.getUUID().length()-ConsoleLog.UUID_LEN)+")"+"\" disconnected", null);
            broadcast(retMsg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}