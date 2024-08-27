package top.certstone;

public class ConsoleLog {
    private static String getSenderName(Massage msg) {
        if (msg.getSender() == null) {
            return "Server";
        } else {
            return msg.getSender().getName()+"("+msg.getSender().getUUID().substring(msg.getSender().getUUID().length()-12)+")";
        }
    }


    // 1、sendMsg
    public static void sendMsg(Massage msg) {
        System.out.println("[INFO]sendMsg: " + getSenderName(msg) + " to " + msg.getReceiver().getName()+"("+msg.getSender().getUUID().substring(msg.getSender().getUUID().length()-12)+")" + " : " + msg.getContent());
    }

    // 2、broadcast
    public static void broadcast(Massage msg) {
        System.out.println("[INFO]broadcast: " + getSenderName(msg) + " to all : " + msg.getContent());
    }
}
