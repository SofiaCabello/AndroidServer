package org.example.json;

import java.util.List;

public class JSONLoginHandler extends JSONHandler{

    //处理登陆JSON
    @Override
    public List<String> handleJSON(String json) {
        List<String> usnm_pswd = null;
        LoginRequest loginRequest = gson.fromJson(json, LoginRequest.class);
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        usnm_pswd.add(username);
        usnm_pswd.add(password);
        return usnm_pswd;
    }

    class LoginRequest{
        private String username;
        private String password;
        public LoginRequest(){
        }
        public String getUsername(){
            return username;
        }
        public String getPassword(){
            return password;
        }
    }
}
