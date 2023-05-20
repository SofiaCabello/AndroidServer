package org.example.database;

import java.sql.SQLException;

/**
 * Created by: 神楽坂千紗
 * 这个类用以处理用户表user，包括添加用户。
 * 只用于注册时添加用户，不用于登录。
 */
public class DBUserRegisterHandler extends DBHandler{
    public DBUserRegisterHandler() throws SQLException {
        super();
    }

    //添加用户
    public boolean addUser(String username, String password,String gender,String photoId,String signature) throws SQLException{
        String checkExistQuery = "SELECT * FROM user WHERE user_name = '" + username + "'";
        String checkMaxIdQuery = "SELECT MAX(user_id) FROM user ";
        int maxId = Integer.parseInt(executeQuery(checkMaxIdQuery).getString(1));
        maxId++;
        String addUserQuery = "INSERT INTO user (user_name,user_gender,password,user_photoid,user_signature,user_id) " +
                "VALUES ('"+ username +"',"+ gender +",'"+ password +"','"+ photoId +"','"+ signature +"',"+ maxId +")";
        if(executeQuery(checkExistQuery).next()){
            return false;
        }else{
            executeUpdate(addUserQuery);
            return true;
        }
    }
}
