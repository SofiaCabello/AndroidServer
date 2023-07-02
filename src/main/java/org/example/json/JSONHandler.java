package org.example.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.database.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JSONHandler {
    private Gson gson;
    public static final String TYPE_TEXT = "0";
    public static final String TYPE_IMAGE = "1";
    public static final String TYPE_AUDIO = "2";
    public static final String TYPE_FILE = "3";
    public static final String TYPE_VIDEO = "4";
    public static final String TYPE_ADD_FRIEND = "5";


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
        return  new DBUserLoginHandler().checkUser(username, password);
    }

    public boolean handleRegisterRequest(JsonObject json) throws SQLException{
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        String photoId = json.get("photoId").getAsString();
        String signature = json.get("signature").getAsString();
        return new DBUserRegisterHandler().addUser(username, password, photoId, signature);
    }

    public String handleAddUserRequest(JsonObject json) throws SQLException{
        String username = json.get("username").getAsString();
        String targetUsername = json.get("targetUsername").getAsString();
        String friendInfo = new DBAddFriendHandler().getFriendInfo(targetUsername);
        System.out.println("friendInfo: " + friendInfo);
        // 根据JSON字符串格式的查询结果friendInfo，来构造响应的JSON字符串
        JsonObject jsonObject = getJsonObject(friendInfo);

        JsonObject friendInfoJson = new JsonObject();
        friendInfoJson.addProperty("type", "addFriend");
        friendInfoJson.addProperty("friendName", jsonObject.get("user_name").getAsString());
        friendInfoJson.addProperty("friendPhotoId",jsonObject.get("user_photoid").getAsString());
        friendInfoJson.addProperty("friendSignature",jsonObject.get("user_signature").getAsString());
        return getJsonString(friendInfoJson);
    }


    public String handleGetMessageRequest(JsonObject json) throws SQLException{
        String receiveId = json.get("receiveId").getAsString();
        DBMessageHandler handler = new DBMessageHandler();
        String messages = handler.getMessagesJSON(receiveId);
        System.out.println("messages: " + messages);
        //JsonObject jsonObject = new JsonObject(messages);
        handler.deleteMessage(receiveId);
        return messages;
    }

    public void handlePostMessageRequest(JsonObject json) throws SQLException{
        String sendId = json.get("sendId").getAsString();
        String receiveId = json.get("receiveId").getAsString();
        String content = json.get("content").getAsString();
        new DBMessageHandler().addMessage(sendId, receiveId, content, TYPE_TEXT);
    }

    public void handleAddConfirmRequest(JsonObject json) throws SQLException{
        String username = json.get("username").getAsString();
        String targetUsername = json.get("targetUsername").getAsString();
        new DBMessageHandler().addMessage(username, targetUsername, "null", TYPE_ADD_FRIEND);
    }

    public void handleUpdateAvatarRequest(JsonObject json) throws SQLException{
        String username = json.get("username").getAsString();
        String photoId = json.get("photoId").getAsString();
        new DBUpdateHandler().update("photoId",username,photoId);
    }

    public void handleUpdateSignatureRequest(JsonObject json) throws SQLException{
        String username = json.get("username").getAsString();
        String signature = json.get("signature").getAsString();
        new DBUpdateHandler().update("signature",username,signature);
    }

    public String handleGetUserInfoRequest(JsonObject json) throws SQLException{
        String username = json.get("username").getAsString();
        String userInfo = new DBUserInfoHandler().getUserInfo(username);
        JsonObject jsonObject = getJsonObject(userInfo);
        JsonObject userInfoJson = new JsonObject();
        userInfoJson.addProperty("type", "userInfo");
        userInfoJson.addProperty("photoId",jsonObject.get("user_photoid").getAsString());
        userInfoJson.addProperty("signature",jsonObject.get("user_signature").getAsString());
        return getJsonString(userInfoJson);
    }

    public void handlePostBase64Request(JsonObject json) throws SQLException{
        String sendId = json.get("sendId").getAsString();
        String receiveId = json.get("receiveId").getAsString();
        String content = json.get("content").getAsString();
        String type = json.get("fileType").getAsString();

        if(Objects.equals(type, TYPE_AUDIO)){
            new DBMessageHandler().addMessage(sendId, receiveId, content, TYPE_AUDIO);
        } else if (Objects.equals(type, TYPE_VIDEO)) {
            new DBMessageHandler().addMessage(sendId, receiveId, content, TYPE_VIDEO);
        } else if (Objects.equals(type, TYPE_FILE)) {
            new DBMessageHandler().addMessage(sendId, receiveId, content, TYPE_FILE);
        } else if (Objects.equals(type, TYPE_IMAGE)) {
            new DBMessageHandler().addMessage(sendId, receiveId, content, TYPE_IMAGE);
        }
    }
}
