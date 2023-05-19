# AndroidServer
为仿微信项目设计的服务器端程序

​		这个项目是[Android期末大作业](https://github.com/SofiaCabello/2023SEEE-SofiaGroup)设计的服务器端程序，基于Servlet和MySQL构建。它主要实现了以下的功能：

1. 登陆验证
2. 消息发送（POST）
3. 消息接收（GET）

​		由于Servlet中服务器处于被动地位，无法主动向客户端推送信息，所以接受发送信息都需要客户端主动向服务器发起请求。这固然造成了一定的不便和安全性问题，但对于我们来说已经是目前能够做到的最好的情况了。即便如此，这个架构设计也避免了在数据库中维护用户-连接表的问题，使得消息转发变得方便。

​		消息的传递基于JSON实现。JSON首行是类型，服务器通过判断类型来选择执行不同的功能。

![未命名绘图.drawio](/Users/jintaixi/Desktop/Android 大作业/未命名绘图.drawio.svg)

​		GET方法原理也是类似的。
