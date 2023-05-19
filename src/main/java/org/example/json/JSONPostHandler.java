package org.example.json;

import java.util.List;

public class JSONPostHandler extends JSONHandler{
    @Override
    public List<String> handleJSON(String json) {
        List<String> messageInfo = null;
        PostRequest messageRequest = gson.fromJson(json, PostRequest.class);
        String destUsername = messageRequest.getDestUsername();
        String message = messageRequest.getMessage();
        messageInfo.add(destUsername);
        messageInfo.add(message);
        return messageInfo;
    }

    class PostRequest {
        private String destUsername;
        private String message;
        public PostRequest(){
        }
        public String getDestUsername(){
            return destUsername;
        }
        public String getMessage(){
            return message;
        }
    }
}
