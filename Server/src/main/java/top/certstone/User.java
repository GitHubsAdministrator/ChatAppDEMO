package top.certstone;

import java.io.Serializable;

/**
 * 用户类，用于存储用户信息
 * the class is used to store user information
 */

public class User implements Serializable {

    /**
     * 用户属性，包括用户名和UUID
     * user attributes, including name and UUID
     */
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
//        return "User{" +
//                "name='" + name + '\'' +
//                ", UUID='" + UUID + '\'' +
//                '}';
        return name+"("+getUUID().substring(getUUID().length()-ConsoleLog.UUID_LEN)+")";
    }
}
