package top.certstone;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String UUID;

    public User(String name) {
        this.name = name;
        this.UUID = java.util.UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getUUID() {
        return UUID;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", UUID='" + UUID + '\'' +
                '}';
    }
}
