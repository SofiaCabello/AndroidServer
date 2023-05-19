package org.example.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBMessageHandler extends DBHandler {
    public DBMessageHandler(String url, String username, String password) throws SQLException {
        super(url, username, password);
    }

    //添加未读消息到列表中
    public void addMessage(String username, String message) throws SQLException{
        String query = "INSERT INTO messages (username, message) VALUES ('" + username + "', '" + message + "')";
        executeUpdate(query);
    }

    //删除已读消息
    public void deleteMessage(String username, String message) throws SQLException{
        String query = "DELETE FROM messages WHERE username = '" + username + "' AND message = '" + message + "'";
        executeUpdate(query);
    }

    //获取未读消息列表
    public List<String> getMessages(String username) throws SQLException{
        String query = "SELECT * FROM messages WHERE username = '" + username + "'";
        ResultSet resultSet = executeQuery(query);
        List<String> messages = resultSetToList(resultSet);
        return messages;
    }

}
