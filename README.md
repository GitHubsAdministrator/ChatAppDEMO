# ChatAppDEMO

> 一个简单的Java编写的C/S架构的聊天室软件
> 
> A simple chatroom software with C/S architecture written in Java
> 
交作业的项目，写的很烂各位轻点喷🥲   
The project submitted for homework is quite poor. Please be gentle when criticizing it.🥲



## Status

未完工，还在开发

Not finished yet, still in development.



## Preview

(Preview will be added after the project is completed)



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



## Usage(English)

Firstly, you need to build the project with Maven.

```
$ mvn clean package
```

Then you can find the jar file in the `target` directory.Then you can run the jar file.

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
```



## 食用方法(中文)

首先，你需要使用Maven构建项目（当前项目还在开发，后续会在 release 提供编译好的jar——甚至exe）。

```
$ mvn clean package
```

然后你可以在`target`目录下找到jar文件，然后运行它。

客户端：直接使用`java -jar Client-1.0-SNAPSHOT-jar-with-dependencies.jar`命令运行

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
```



## TODO

- [x] 完成基本的C/S架构
- [x] 优化UI
- [x] 在线用户列表
- [x] 私聊功能
- [ ] 支持传递文件
- [ ] 服务端可更改聊天室名称
- [ ] 客户端生成配置文件及日志保存
- [ ] 服务端日志保存
- [ ] 服务端命令支持(like Minecraft)
- [ ] 表情包

---

- 碎碎念s

*吸取到的教训：开发前要先把架构设计好，具体到类以及类的功能、属性；否则就会像我一样写成依托 ：(*

项目设计的灵感其实来自于 Minecraft 的聊天方式：大家都在一个服务器里聊天，但是又有私聊的功能。连接密钥对应着 Minecraft 服务器的白名单功能，同时我使用GUI代替了mc中的/msg命令。

（不过，UI设计还是仿照了QQ）