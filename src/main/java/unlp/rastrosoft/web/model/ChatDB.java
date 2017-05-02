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
        String sql = "SELECT message_chat.id, name, message, datetime, message_chat.enabled  FROM message_chat INNER JOIN users ON (message_chat.from= users.id) WHERE ((datetime) >= SUBTIME(CURDATE(), TIME('12:00:00')))  ORDER BY datetime ASC";
        //String sql = "SELECT enabled INTO @chat_enabled FROM chat WHERE id =1; SELECT message_chat.id, name, message, datetime, message_chat.enabled, @chat_enabled  FROM message_chat INNER JOIN users ON (message_chat.from= users.id) WHERE ((@chat_enabled=1)AND (datetime) >= SUBTIME(CURDATE(), TIME('12:00:00')))  ORDER BY datetime ASC;";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
           
            List<List<String>> chats = new ArrayList<>();
            
            while (rs.next()) {
                List<String> chat = new ArrayList<>();
                chat.add(String.valueOf(rs.getInt("id")));
                chat.add(rs.getString("name"));
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
     public List<String> insertMessage(int from, String message){
        
        String sql = "INSERT INTO message_chat (`from`, `message`) VALUES (?, ?)";
        Connection conn = null;
        List<String> chat = new ArrayList<>();
        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, from);
                ps.setString(2, message);
                ps.executeUpdate();
                int message_id = (int) ps.getLastInsertID();
                ps.close();
//                sql = "UPDATE chat SET newmessage = 1 WHERE id = 1";
//                ps = (PreparedStatement) conn.prepareStatement(sql);
//                ps.executeUpdate();
                sql = "SELECT message, name, datetime, message_chat.enabled FROM message_chat INNER JOIN users ON `from` = users.id  WHERE `from` = ? AND message_chat.id = ?";
                ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1, from);
                ps.setInt(2, message_id);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {                    
                    chat.add(rs.getString("name"));
                    chat.add(rs.getString("message"));
                    chat.add(rs.getString("datetime"));
                    chat.add(rs.getString("enabled"));
                }
                ps.close();
                return chat;
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
    public boolean hasNewMessage(){
        String sql = "SELECT newmessage FROM chat WHERE id = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            Boolean  newmessage = false;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("newmessage") == 1){
                    newmessage = true;
                    sql = "UPDATE chat SET newmessage = 0 WHERE id = 1";
                    ps = (PreparedStatement) conn.prepareStatement(sql);
                    ps.executeUpdate();
                    
                }else{
                    newmessage = false;
                }
            }
            
            rs.close();
            ps.close();
            return newmessage;

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
    public boolean enableChat(){
        String sql = "UPDATE chat SET enabled = 1 WHERE id = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            
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
        return true;
    }
     public boolean disableChat(){
        String sql = "UPDATE chat SET enabled = 0 WHERE id = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            
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
        return true;
    }
    public boolean isEnabled(){
        String sql = "SELECT enabled FROM chat WHERE id = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            Boolean  enabled = false;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("enabled") == 1){
                    enabled = true;
                }else{
                    enabled = false;
                }
            }
            rs.close();
            ps.close();
            return enabled;

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
    public boolean deleteChat(){
        String sql = "DELETE FROM message_chat";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            
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
        return true;
    }
}
