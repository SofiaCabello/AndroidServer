package org.example.database;

import java.sql.SQLException;

public class DBAddFriendHandler extends DBHandler{
    public DBAddFriendHandler() throws SQLException {
        super();
    }

    public boolean userExist(String username) throws SQLException{
        String query = "SELECT * FROM user WHERE user_name = '" + username + "'";
        return executeQuery(query).next();
    }

    public String getFriendInfo(String username) throws SQLException{
        if(!userExist(username)){
            return null;
        }else {
            String query = "SELECT  user_name , user_photoid, user_signature FROM user WHERE user_name = '" + username + "'";
            return resultSetToJSON(executeQuery(query));
        }
    }
}
