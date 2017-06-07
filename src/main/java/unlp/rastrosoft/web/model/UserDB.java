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

/**
 *
 * @author ip300
 */
public class UserDB extends Database{
    public void insertUser(String username, String password, String name, String lastname, String mail, String hash){
        
        String sqlRole = "INSERT INTO user_roles " +
                      "(username, role) VALUES (?, ?)";
        
        String sql = "INSERT INTO users " +
                      "(username, password, name, lastname, mail, hash) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, name);
                ps.setString(4, lastname);
                ps.setString(5, mail);
                ps.setString(6, hash);
                ps.executeUpdate();
                
                ps = (PreparedStatement) conn.prepareStatement(sqlRole);
                ps.setString(1, username);
                ps.setInt(2, 3);
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
    public void modifyUser(int id, String username, String password, String enabled, String name, String lastname, String mail){
        
        String sql = "UPDATE users " +
                      "SET username = ? , password = ?, enabled = ?, name = ?, lastname = ?, mail = ? WHERE id = ?";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, enabled);
                ps.setString(4, name);
                ps.setString(5, lastname);
                ps.setString(6, mail);
                ps.setInt(7, id);
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
    public void removeUser(int id){
        
        String sql = "DELETE FROM users " +
                      "WHERE id = ?";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, id);
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

    public User getUser(int id){
        String sql = "SELECT * FROM users WHERE id = ? LIMIT 1";
        Connection conn = null;
        User user = new User();
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEnabled(rs.getString("enabled"));
                user.setName(rs.getString("name"));                
                user.setMail(rs.getString("mail"));   
            }
            rs.close();
            ps.close();
            return user;

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
    public User getUser(String username){
        String sql = "SELECT * FROM users WHERE username = ? LIMIT 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs =ps.executeQuery();
            User user = null;

            if (rs.next()) {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("enabled"),
                    rs.getString("name"),
                    rs.getString("lastname"),
                    rs.getString("mail")
                );
            }


            rs.close();
            ps.close();
            return user;

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
