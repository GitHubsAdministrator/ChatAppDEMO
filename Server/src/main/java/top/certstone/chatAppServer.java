package top.certstone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class chatAppServer {

    public static Vector<ServerThread> clients = new Vector<>(); // Create a vector to store clients

    public static void main(String[] args) {

        // 获取命令行参数-p (or --port)
        int port = 61000;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p") || args[i].equals("--port")) {
                port = Integer.parseInt(args[i + 1]);
            }
        }

        //获取密钥参数-k(or --key)
        String key = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-k") || args[i].equals("--key")) {
                key = args[i + 1];
            }
        }

        try { // Create a server socket
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                ServerThread serverThread = new ServerThread(server.accept());
                serverThread.start();
                clients.add(serverThread);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Define the ServerThread class
class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        // Handle client connection
        try {
            while (true) {
                // Receive message from the client
                String message = new DataInputStream(socket.getInputStream()).readUTF();
                System.out.println("Received: " + message);
                // Send message to all clients
                for (ServerThread client : chatAppServer.clients) {
                    new DataOutputStream(client.socket.getOutputStream()).writeUTF(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}