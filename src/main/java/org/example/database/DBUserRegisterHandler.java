package org.example.database;

import java.sql.ResultSet;
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
    public boolean addUser(String username, String password, String photoId, String signature) throws SQLException{
        String checkExistQuery = "SELECT * FROM user WHERE user_name = '" + username + "'";
        String addUserQuery = "INSERT INTO user (user_name,password,user_photoid,user_signature) "+
                "VALUES ('" + username + "','" + password + "','" + photoId + "','" + signature + "')";

        //检查用户名是否已存在
        ResultSet existResult = executeQuery(checkExistQuery);
        if(existResult.next()){
            existResult.close();
            return false;
        }
        existResult.close();

        //添加用户
        executeUpdate(addUserQuery);

        //添加用户成功
        return true;
    }
}
