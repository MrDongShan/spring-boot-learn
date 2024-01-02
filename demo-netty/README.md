##
---

> 本demo用于创建一个netty-tcp的客户端

### 代码

#### pom文件

```xml

<dependency>
    <groupId>io.netty</groupId>
    <artifactId>netty-all</artifactId>
    <version>${netty.version}</version>
</dependency>
```

#### [TcpServerHandler.java](src%2Fmain%2Fjava%2Fcom%2Fmrdongshan%2Fnetty%2Fhandler%2FTcpServerHandler.java)
---

创建一个TCP服务端，同时指定启动服务使用的端口为6666

#### [TcpServerHandler.java](src%2Fmain%2Fjava%2Fcom%2Fmrdongshan%2Fnetty%2Fhandler%2FTcpServerHandler.java)
---
创建一个TCP服务端的消息处理器，可以接收处理消息，并回复消息给客户端

#### [TcpClientService.java](src%2Fmain%2Fjava%2Fcom%2Fmrdongshan%2Fnetty%2Fservice%2FTcpClientService.java)
---

创建一个TCP客户端，同时指定服务端地址为 127.0.0.1:6666
`ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6666).sync();`

#### [TcpClientHandler.java](src%2Fmain%2Fjava%2Fcom%2Fmrdongshan%2Fnetty%2Fhandler%2FTcpClientHandler.java)
---

用于接收和处理TCP服务端发来的消息，还可以发送消息到服务端

#### [TestController.java](src%2Fmain%2Fjava%2Fcom%2Fmrdongshan%2Fnetty%2Fcontroller%2FTestController.java)
---

创建 server 服务端, http://localhost:8080/start/tcp/service/demo1

创建 client 客户端, http://localhost:8080/start/tcp/client/demo1