package org.example.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by: 神楽坂千紗
 * 这个类用以处理消息表chat_info，包括添加消息、删除消息、获取消息列表。
 */
public class DBMessageHandler extends DBHandler {
    public DBMessageHandler() throws SQLException{
        super();
    }

    //添加未读消息到列表中
    //表unread_message(TS, sendId, receiveId, type, content)
    public void addMessage(String sendId, String receiveId, String content, int type) throws SQLException{
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String query =
                "INSERT INTO unread_message(TS, sendId, receiveId, type, content) " +
                        "VALUES ('" + timeStamp + "', '" + sendId + "', '" + receiveId + "', '" + type + "', '" + content + "')";
        executeUpdate(query);
    }

    //删除已读消息
    public void deleteMessage(String reveiveId) throws SQLException{
        String query = "DELETE FROM chat_info WHERE info_receiveid = '" + reveiveId + "'";
        executeUpdate(query);
    }

    //获取未读消息列表
    public List<Map<String, Object>> getMessages(String receiveId) throws SQLException{
        String query = "SELECT info_sendid,info_content,info_TS FROM chat_info WHERE info_receiveid = '" + receiveId + "'";
        ResultSet resultSet = executeQuery(query);
        return resultSetToList(resultSet);
    }

}
