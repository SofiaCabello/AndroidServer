package org.example.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.database.DBMessageHandler;
import org.example.database.DBUserLoginHandler;
import org.example.database.DBUserRegisterHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JSONHandler {
    private Gson gson;

    public JSONHandler(){
        gson = new Gson();
    }

    public JsonObject getJsonObject(String json){
        return gson.fromJson(json, JsonObject.class);
    }

    public String getJsonString(Object object){
        return gson.toJson(object);
    }

    public boolean handleLoginRequest(JsonObject json) throws SQLException {
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        return new DBUserLoginHandler().checkUser(username, password);
    }

    public boolean handleRegisterRequest(JsonObject json) throws SQLException{
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        String gender = json.get("usergender").getAsString();
        String photoId = json.get("photoId").getAsString();
        String signature = json.get("signature").getAsString();
        return new DBUserRegisterHandler().addUser(username, password, gender, photoId, signature);
    }

    public List<Map<String,Object>> handleGetMessageRequest(JsonObject json) throws SQLException{
        String receiveId = json.get("receiveId").getAsString();
        DBMessageHandler handler = new DBMessageHandler();
        List<Map<String, Object>> messages = handler.getMessages(receiveId);
        handler.deleteMessage(receiveId);
        return messages;
    }

    public void handlePostMessageRequest(JsonObject json) throws SQLException{
        String sendId = json.get("sendId").getAsString();
        String receiveId = json.get("receiveId").getAsString();
        String content = json.get("content").getAsString();
        new DBMessageHandler().addMessage(sendId, receiveId, content);
    }


}
