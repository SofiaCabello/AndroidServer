package org.example.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserLoginHandler extends DBHandler {
    public DBUserLoginHandler(String url, String username, String password) throws SQLException {
        super(url, username, password);
    }

    public boolean checkUser(String username, String password) throws SQLException{
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet resultSet = executeQuery(query);
        boolean loginSuccessful = resultSet.next();
        resultSet.close();
        return loginSuccessful;
    }
}
