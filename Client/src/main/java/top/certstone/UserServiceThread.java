package top.certstone;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserServiceThread extends Thread {
    private Socket socket;
    private static User user;
    public ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
    public ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

    public UserServiceThread(Socket socket, User user) throws IOException {
        this.socket = socket;
        this.user = user;
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
            return null;
        }
    }



    @Override
    public void run() {
        //checkInfo();
        while (true) {
            Massage msg = receiveMsg();
            if (msg == null) {
                break;
            }
            if (msg.getType() == 0) {
                //broadcast(msg);
            } else {
                //sendMsg(msg);
            }
        }
    }


}
