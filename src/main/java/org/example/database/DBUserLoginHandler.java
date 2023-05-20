package org.example.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by: 神楽坂千紗
 * 这个类用以处理用户表user，包括检查用户合法性。
 * 只用于登录时检查用户合法性，不用于注册。
 */
public class DBUserLoginHandler extends DBHandler {
    public DBUserLoginHandler() throws SQLException {
        super();
    }

    //检查用户合法性
    public boolean checkUser(String username, String password) throws SQLException{
        String query = "SELECT * FROM user " +
                "WHERE user_name = '" + username + "' AND password = '" + password + "'";
        ResultSet resultSet = executeQuery(query);
        boolean loginSuccessful = resultSet.next();
        resultSet.close();
        return loginSuccessful;
    }
}
