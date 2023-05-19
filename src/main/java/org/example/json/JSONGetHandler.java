package org.example.json;

import java.util.List;

public class JSONGetHandler extends JSONHandler{
    @Override
    public List<String> handleJSON(String json) {
        List<String> messageInfo = null;
        GetRequest messageRequest = gson.fromJson(json, GetRequest.class);
        String username = messageRequest.getUsername();
        String message = messageRequest.getMessage();
        messageInfo.add(username);
        messageInfo.add(message);
        return messageInfo;
    }

    class GetRequest{
        private String username;
        private String message;
        public GetRequest(){
        }
        public String getUsername(){
            return username;
        }
        public String getMessage(){
            return message;
        }
    }
}
