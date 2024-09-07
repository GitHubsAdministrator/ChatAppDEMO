package top.certstone;

/**
 * This class is used to print logs to the console
 * 用于向控制台打印日志
 */

public class ConsoleLog {

    public static final int UUID_LEN = 8; // 控制显示的UUID长度

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

    // print logs when someone sending messages
    public static void sendMsg(Massage msg) {
        if (msg.getType() == MassageType.USER_LIST) return;
        System.out.println("[INFO]sendMsg: " + getSenderName(msg) + " to " + getReceiverName(msg) + " : " + msg.getContent());
    }

    // print logs when somebody broadcasts messages
    public static void broadcast(Massage msg) {
        System.out.println("[INFO]broadcast: " + getSenderName(msg) + " to all : " + msg.getContent());
    }
}
