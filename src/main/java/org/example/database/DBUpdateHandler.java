package org.example.database;

import java.sql.SQLException;

public class DBUpdateHandler extends DBHandler{
    public DBUpdateHandler() throws SQLException {
        super();
    }

    public boolean update(String type, String username, String value) throws SQLException{
        if(type.equals("signature")){
            try{
                String query = "UPDATE user SET user_signature = '" + value + "' WHERE user_name = '" + username + "'";
                executeUpdate(query);
            }catch (Exception e){
                return false;
            }
        } else if (type.equals("photoId")) {
            try {
                String query = "UPDATE user SET user_photoid = '" + value + "' WHERE user_name = '" + username + "'";
                executeUpdate(query);
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
