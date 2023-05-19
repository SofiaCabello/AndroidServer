package org.example.servlet;

import com.google.gson.Gson;
import org.example.database.DBMessageHandler;
import org.example.json.JSONHandler;
import org.example.json.JSONPostHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ClientServlet extends HttpServlet{
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getParameter("json");
        String[] lines = json.split("\\r?\\n");
        String typeInfo = lines[0];
        String type = typeInfo.split(":")[1];

        switch (type){
            case "postMessage":
                JSONPostHandler jsonPostHandler = new JSONPostHandler();
                DBMessageHandler dbMessageHandler = null;
                try{
                    dbMessageHandler = new DBMessageHandler(DB_URL,jsonPostHandler.handleJSON(json).get(0),jsonPostHandler.handleJSON(json).get(1));
                } catch (SQLException e){
                    e.printStackTrace();
                }
                break;

        }
    }
}
