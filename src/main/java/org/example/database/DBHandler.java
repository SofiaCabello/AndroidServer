package org.example.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {
    private Connection connection;

    public DBHandler(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public ResultSet executeQuery(String query) throws SQLException{
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException{
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    public void close() throws SQLException{
        connection.close();
    }

    public List<String> resultSetToList(ResultSet resultSet) throws SQLException{
        List<String> list = new ArrayList<>();
        while(resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
