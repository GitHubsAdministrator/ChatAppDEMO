package top.certstone;

public class User {
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
}
