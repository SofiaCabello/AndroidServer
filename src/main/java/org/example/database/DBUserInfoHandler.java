package org.example.database;

import java.sql.SQLException;

public class DBUserInfoHandler extends DBHandler{
    public DBUserInfoHandler() throws SQLException {
        super();
    }

    public String getUserInfo(String username) throws SQLException{
        String query = "SELECT user_photoid, user_signature FROM user WHERE user_name = '" + username + "'";
        return resultSetToJSON(executeQuery(query));
    }
}
