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
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_VIDEO = 3;
    public static final int TYPE_FILE = 4;


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
        new DBMessageHandler().addMessage(sendId, receiveId, content, TYPE_TEXT);
    }

    public void handlePostBase64Request(JsonObject json) throws SQLException{
        String sendId = json.get("sendId").getAsString();
        String receiveId = json.get("receiveId").getAsString();
        String content = json.get("content").getAsString();
        int type = json.get("fileType").getAsInt();
        if(type == TYPE_AUDIO){
            new DBMessageHandler().addMessage(sendId, receiveId, content, TYPE_AUDIO);
        } else if (type == TYPE_VIDEO) {
            new DBMessageHandler().addMessage(sendId, receiveId, content, TYPE_VIDEO);
        } else if (type == TYPE_FILE) {
            new DBMessageHandler().addMessage(sendId, receiveId, content, TYPE_FILE);
        } else if (type == TYPE_IMAGE) {
            new DBMessageHandler().addMessage(sendId, receiveId, content, TYPE_IMAGE);
        }
    }
}
