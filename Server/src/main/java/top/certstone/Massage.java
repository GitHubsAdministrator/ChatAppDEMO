package top.certstone;

import java.io.Serializable;

public class Massage implements Serializable {
    private int type;
    private String content;
    private User sender;
    private User receiver;

    public Massage(int type, String content, User sender) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.receiver = null;
    }
    public Massage(int type, String content, User sender, User receiver) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        String senderName = sender == null ? "null" : sender.getName();
        String receiverName = receiver == null ? "null" : receiver.getName();
        return "Massage{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", sender='" + senderName + '\'' +
                ", receiver='" + receiverName + '\'' +
                '}';
    }
}
