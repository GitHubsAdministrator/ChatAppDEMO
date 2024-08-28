package top.certstone;

public class ConsoleLog {

    static final int UUID_LEN = 8;

    public static String getSenderName(Massage msg) {
        if (msg.getSender() == null) {
            return "Server";
        } else {
            return msg.getSender().getName()+"("+msg.getSender().getUUID().substring(msg.getSender().getUUID().length()-UUID_LEN)+")";
        }
    }

    public static String getReceiverName(Massage msg) {
        if (msg.getReceiver() == null) {
            return "all";
        } else {
            return msg.getReceiver().getName()+"("+msg.getReceiver().getUUID().substring(msg.getReceiver().getUUID().length()-UUID_LEN)+")";
        }
    }

    // sendMsg
    public static void sendMsg(Massage msg) {
        if (msg.getReceiver()!=null)System.out.println("[INFO]sendMsg: " + getSenderName(msg) + " to " + getReceiverName(msg) + " : " + msg.getContent());
        else System.out.println("[INFO]broadcast: " + getSenderName(msg) + " to all : " + msg.getContent());
    }


    public static void receiveMsg(Massage msg) {
        System.out.println("[INFO]receiveMsg: " + getSenderName(msg) + " : " + msg.getContent());
    }
}
