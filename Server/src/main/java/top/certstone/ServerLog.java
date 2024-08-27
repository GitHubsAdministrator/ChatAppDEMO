package top.certstone;

public class ServerLog {
    private static String getSender(Massage msg) {
        if (msg.getSender() == null) {
            return "Server";
        } else {
            return msg.getSender().getName();
        }
    }


    // 1、sendMsg
    public static void sendMsg(Massage msg) {
        System.out.println("[INFO]sendMsg: " + getSender(msg) + " to " + msg.getReceiver().getName() + " : " + msg.getContent());
    }

    // 2、broadcast
    public static void broadcast(Massage msg) {
        System.out.println("[INFO]broadcast: " + getSender(msg) + " to all : " + msg.getContent());
    }
}
