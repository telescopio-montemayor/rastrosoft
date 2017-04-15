/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

import com.mysql.cj.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ip300
 */
public class ChatDB extends Database{
    
    public List<Chat> getChat(){
        String sql = "SELECT * FROM message_chat";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);


            ResultSet rs =ps.executeQuery();
            Chat chat = null;
            List<Chat> chats = new ArrayList<>();
            while (rs.next()) {
                chat = new Chat(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("username"),
                    rs.getString("message"),
                    rs.getString("datetime"),
                    rs.getString("enabled")
                );
                chats.add(chat);
            }


            rs.close();
            ps.close();
            return chats;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        
    }
     public List<List<String>> getChatsAsList(){
        String sql = "SELECT message_chat.id, username, message, datetime, message_chat.enabled  FROM message_chat INNER JOIN users ON (message_chat.from= users.id) WHERE ((datetime) >= SUBTIME(CURDATE(), TIME('24:00:00')))  ORDER BY datetime ASC";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
           
            List<List<String>> chats = new ArrayList<>();
            
            while (rs.next()) {
                List<String> chat = new ArrayList<>();
                chat.add(String.valueOf(rs.getInt("id")));
                chat.add(rs.getString("username"));
                chat.add(rs.getString("message"));
                chat.add(rs.getString("datetime"));
                chat.add(rs.getString("enabled"));
                
                chats.add(chat);
            }


            rs.close();
            ps.close();
            return chats;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        
    }
     public void insertMessage(int from, String message){
        
        String sql = "INSERT INTO message_chat (`from`, `message`) VALUES (?, ?)";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, from);
                ps.setString(2, message);
                ps.executeUpdate();
                ps.close();

        } catch (SQLException e) {
                throw new RuntimeException(e);

        } finally {
                if (conn != null) {
                        try {
                                conn.close();
                        } catch (SQLException e) {}
                }
        }
    } 
}
