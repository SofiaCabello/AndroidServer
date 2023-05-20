package org.example.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: 神楽坂千紗
 * 这个类用以处理数据库的连接，以及执行SQL语句。
 * 这个类无需修改。
 */
public class DBHandler {
    private Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/ChatApp";
    private static final String username = "AndroidServer";
    private static final String password = "server";

    public DBHandler() throws SQLException {
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

    public List<Map<String,Object>> resultSetToList(ResultSet resultset){
        List<Map<String,Object>> list = new ArrayList<>();
        try{
            ResultSetMetaData metaData = resultset.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultset.next()){
                Map<String,Object> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++){
                    String columnName = metaData.getColumnLabel(i);
                    Object value = resultset.getObject(columnName);
                    map.put(columnName,value);
                }
                list.add(map);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
