package top.certstone;

/**
 * This interface is used to define the type of messages
 * 用于定义消息的类型
 */

public interface MassageType {
    int LOGIN = 0; // 登录请求
    int TEXT = 1; // 文本消息
//    int EXIT = 2;
    int FILE = 3; // 文件消息
    int ERROR = 4; // 错误消息
//    int SUCCESS = 5;
    int USER_LIST = 6; // 用于传递用户列表信息
    int ROOM_NAME = 7; // 用于获取和更新房间名
}
