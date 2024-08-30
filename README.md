# ChatAppDEMO

> 一个简单的Java编写的C/S架构的聊天室软件
> 
> A simple chatroom software with C/S architecture written in Java
> 
交作业的项目，写的很烂各位轻点喷🥲   
The project submitted for homework is quite poor. Please be gentle when criticizing it.🥲



## Preview

<div style="text-align: center;">

![Login](https://github.com/user-attachments/assets/3402523d-7545-4833-abd6-2347f0130e96)

![ChatGUI](https://github.com/user-attachments/assets/520e095b-82fe-4a00-9f41-5dea83753740)

![PrivateChat](https://github.com/user-attachments/assets/6eb5f28e-369d-4779-95fa-fe709924952f)

</div>


## Usage(English)

Client ：Just start with `java -jar Client-1.0-SNAPSHOT-jar-with-dependencies.jar`

Server ：almost the same as Client, but there's some parameters you can set .

```
$ java -jar Server-1.0-SNAPSHOT-jar-with-dependencies.jar.jar -h
Usage: <main class> [options]
  Options:
    -h, --help
      Show this help message and exit.
    -p, --port
      Set the port of the server
      Default: 61000
    -k, --key
      Set the key when connecting to the server
      Default: null
    -n, --name
      Set the name of the chatroom
      Default: "Chatroom name"
```



## 食用方法(中文)

客户端：使用`java -jar Client-1.0-SNAPSHOT-jar-with-dependencies.jar`命令运行

服务端：和客户端类似，但是有一些参数可以设置。

```
$ java -jar Server-1.0-SNAPSHOT-jar-with-dependencies.jar -h
用法: <main class> [options]
  Options:
    -h, --help
        显示此帮助消息并退出
    -p, --port
        设置服务器的端口
        默认: 61000
    -k, --key
        设置连接服务器时的密钥
        默认: null
    -n, --name
        设置聊天室的名称
        默认: "Chatroom name"
```

## Compile

The project is built with Maven, so you can use `mvn clean package` in the root directory to compile the project. The compiled jar files are in the `target` directory of each module.

项目使用Maven构建，所以你可以在根目录使用`mvn clean package`来编译项目。编译后的jar文件在每个模块的`target`目录下。

## Structure
> 项目结构
>
```
ChatAppDEMO (root)
├─Client (module)
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │   └─top.certstone
│  │  │  │       ├─chatAppClient (main function)
│  │  │  │       ├─LoginGUI
│  │  │  │       ├─ChatGUI
│  │  │  │       ├─UserServiceThread
│  │  │  │       ├─WarnMessage
│  │  │  │       ├─ConsoleLog
│  │  │  │       ├─MessageRenderer
│  │  │  │       ├─CustomListCellRenderer
│  │  │  │       ├─PrivateChatGUI
│  │  │  │       ├─Message (common class)
│  │  │  │       └─User (common class)
│  │  │  └─resources (resource files)
│  │  └─test (Not important)
│  └─pom.xml
├─Server (module)
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │   └─top.certstone
│  │  │  │       ├─chatAppServer (main function)
│  │  │  │       ├─ServerThread
│  │  │  │       ├─ConsoleLog
│  │  │  │       ├─User (common class)
│  │  │  │       └─Message (common class)
│  │  │  └─resources (resource files)
│  │  └─test (Not important)
│  └─pom.xml
└─pom.xml
```



## TODO

- [x] 完成基本的C/S架构
- [x] 优化UI
- [x] 在线用户列表
- [x] 私聊功能
- [ ] 支持传递文件
- [x] 服务端可更改聊天室名称
- [ ] 客户端生成配置文件及日志保存
- [ ] 服务端日志保存
- [ ] 服务端命令支持(like Minecraft)
- [ ] 支持发送图片
- [ ] 表情包

---

- 碎碎念

*吸取到的教训：开发前要先把架构设计好，具体到类以及类的功能、属性；否则就会像我一样写成依托 ：(*

项目设计的灵感其实来自于 Minecraft 的聊天方式：大家都在一个服务器里聊天，但是又有私聊的功能。连接密钥对应着 Minecraft 服务器的白名单功能，同时我使用GUI代替了mc中的/msg命令。

（不过，UI设计还是仿照了QQ）
