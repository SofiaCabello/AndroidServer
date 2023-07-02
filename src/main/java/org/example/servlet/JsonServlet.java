package org.example.servlet;

import com.google.gson.JsonObject;
import org.example.json.JSONHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public class JsonServlet extends HttpServlet {
    private JSONHandler jsonHandler;

    @Override
    public void init() throws ServletException {
        super.init();
        jsonHandler = new JSONHandler();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder requestData = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestData.append(line);
        }
        String json = requestData.toString();
        System.out.println(json);
        JsonObject jsonObject = jsonHandler.getJsonObject(json);
        if (jsonObject.has("type")) {
            String type = jsonObject.get("type").getAsString();
            switch (type) {
                case "login":
                    try {
                        boolean result = jsonHandler.handleLoginRequest(jsonObject);
                        response.getWriter().write(String.valueOf(result));
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.getWriter().write("登陆合法性验证失败");
                    }
                    break;
                case "register":
                    try {
                        boolean result = jsonHandler.handleRegisterRequest(jsonObject);
                        response.getWriter().write(String.valueOf(result));
                        //打印服务器的响应
                        System.out.println("服务器的响应：" + String.valueOf(result));
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.getWriter().write("注册失败");
                    }
                    break;
                case "postMessage":
                    try {
                        jsonHandler.handlePostMessageRequest(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.getWriter().write("发送消息失败");
                    }
                    break;
                case "postBase64":
                    try {
                        jsonHandler.handlePostBase64Request(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "addFriend":
                    try {
                        String result = jsonHandler.handleAddUserRequest(jsonObject);
                        response.getWriter().write(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "getContent":
                    try {
                        String result = jsonHandler.handleGetMessageRequest(jsonObject);
                        System.out.println("getContent:" + result);
                        String jsonString = jsonHandler.getJsonString(result);
                        System.out.println("经过处理的:" + jsonString);
                        response.getWriter().write(jsonString);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.getWriter().write("获取消息失败 Failed to get message");
                    }
                    break;
                case "addConfirm":
                    try {
                        jsonHandler.handleAddConfirmRequest(jsonObject);
                        response.getWriter().write("true");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "updateAvatar":
                    try {
                        jsonHandler.handleUpdateAvatarRequest(jsonObject);
                        response.getWriter().write("success");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "requestUserInfo":
                    try {
                        String result = jsonHandler.handleGetUserInfoRequest(jsonObject);
                        response.getWriter().write(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "updateSignature":
                    try {
                        jsonHandler.handleUpdateSignatureRequest(jsonObject);
                        response.getWriter().write("success");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    response.getWriter().write("请求类型错误");
                    break;
            }
        }
    }
}

