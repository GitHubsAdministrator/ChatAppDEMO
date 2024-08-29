package top.certstone;

import java.io.Serializable;
import java.sql.Time;
import java.util.Vector;

public class Massage implements Serializable {
    private int type;
    private String content = null;
    private User sender = null;
    private User receiver = null;
    public Time time;
    private Vector<User> users = null;
    public Boolean isPrivate = false;

    public Massage(int type, String content, User sender) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.receiver = null;
        this.time = new Time(System.currentTimeMillis());
    }
    public Massage(int type, String content, User sender, User receiver) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.time = new Time(System.currentTimeMillis());
    }

    public Massage(int type, String content, User sender, User receiver, Vector<User> users) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.time = new Time(System.currentTimeMillis());
        this.users = users;
    }

    public Massage(int type, String content, User sender, User receiver, Boolean isPrivate) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.time = new Time(System.currentTimeMillis());
        this.isPrivate = isPrivate;
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

    public Vector<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        if (this.type == MassageType.USER_LIST) return "Massage{" + "type=" + type + ", users=" + users + '}';

        String senderName = sender == null ? "null" : sender.getName();
        String receiverName = receiver == null ? "null" : receiver.getName();

        return "Massage{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", sender='" + senderName + '\'' +
                ", receiver='" + receiverName + '\'' +
                ", time=" + time +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
