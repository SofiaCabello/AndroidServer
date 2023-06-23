# 关于JSON格式的说明

在一些请求中，利用JSON完成信息传递的工作，这里对JSON格式做一些说明。

## 接收消息请求

```json
{
  "type": "getContent",
  "receiveId": "receiveId"
}
```

## 发送消息请求

```json
{
  "type": "postMessage",
  "sendId": "sendId",
  "receiveId": "receiveId",
  "content": "content"
}
```

## 注册请求

```json
{
  "type": "register",
  "username": "username",
  "password": "password",
  "usergender": "usergender",
  "photoId": "photoId",
  "signature": "signature"
}
```

## 登录请求

```json
{
  "type": "login",
  "username": "username",
  "password": "password"
}
```

## base64编码二进制文件传送请求

```json
{
  "type":"postBase64",
  "sendId":"SendId",
  "receiveId":"receiveId",
  "fileType":"fileType",
  "fileName": "fileName",
  "content":"content"
}
```

## 响应消息
```json
{
  "sendId": "sendId",
  "type": "type",
  "TS": "TS",
  "content": "content"
}
```

