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
    public void insertUser(String username, String password, String role){
        
        String sql = "INSERT INTO users " +
                      "(username, password, role) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, role);
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
    public void modifyUser(int id, String username, String password, String role){
        
        String sql = "UPDATE users " +
                      "SET username = ? , password = ?, role = ? WHERE id = ?";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, role);
                ps.setInt(4, id);
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
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =ps.executeQuery();
            User user = null;

            if (rs.next()) {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
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
                    rs.getString("role")
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
