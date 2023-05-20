package org.example.servlet;

import com.google.gson.JsonObject;
import org.example.json.JSONHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonServlet extends HttpServlet{
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private JSONHandler jsonHandler;
    @Override
    public void init() throws ServletException {
        super.init();
        jsonHandler = new JSONHandler();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getParameter("json");
        JsonObject jsonObject = jsonHandler.getJsonObject(json);
        if (jsonObject.has("type")) {
            String type = jsonObject.get("type").getAsString();
            if (type.equals("login")) {
                try {
                    boolean result = jsonHandler.handleLoginRequest(jsonObject);
                    response.getWriter().write(String.valueOf(result));
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().write("登陆合法性验证失败");
                }
            } else if (type.equals("register")) {
                try {
                    boolean result = jsonHandler.handleRegisterRequest(jsonObject);
                    response.getWriter().write(String.valueOf(result));
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().write("注册失败");
                }
            } else if (type.equals("getMessage")) {
                try {
                    response.getWriter().write(jsonHandler.getJsonString(jsonHandler.handleGetMessageRequest(jsonObject)));
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().write("获取消息失败");
                }
            } else if (type.equals("postMessage")) {
                try {
                    jsonHandler.handlePostMessageRequest(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().write("发送消息失败");
                }
            }
        }else{
            response.getWriter().write("请求类型错误");
        }

    }

}

