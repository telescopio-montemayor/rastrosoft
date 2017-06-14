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
                      "SET username = ? , password = ?, enabled = ?, name = ?, lastname = ?, mail = ? WHERE id = ? LIMIT 1";
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
    public void deleteUser(int id){
        
        String sql = "UPDATE users " +
                      "SET  enabled = 0 WHERE id = ? LIMIT 1";
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
                user.setLastname(rs.getString("lastname"));
                user.setMail(rs.getString("mail")); 
                user.setCredits(rs.getInt("credits"));
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
                    rs.getString("mail"),
                    rs.getInt("credits")
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
    public boolean validateMail(String hash, String mail){
        
        String sql = "UPDATE users " +
                      "SET enabled = 1, hash = -1 WHERE hash = ? AND mail = ? AND enabled <> -1 LIMIT 1";
        Connection conn = null;
        int result = 0;
        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setString(1, hash);
                ps.setString(2, mail);
                result = ps.executeUpdate();
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
        return (result != 0);
    }
    public List<List<String>> getModerationUsers(int enabled, int role){
        String sql = "SELECT DISTINCT (id),users.username, enabled, name, lastname, mail, credits, role  FROM users INNER JOIN user_roles ON users.username = user_roles.username WHERE users.enabled = ? AND user_roles.role = ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, enabled);
            ps.setInt(2, role);
            ResultSet rs =ps.executeQuery();
            List<List<String>> users = new ArrayList<>();

            while (rs.next()) {
                    ArrayList<String> user = new ArrayList<>();
                    user.add(String.valueOf(rs.getInt("id")));
                    user.add(rs.getString("username"));
                    user.add(rs.getString("name"));
                    user.add(rs.getString("lastname"));
                    user.add(rs.getString("mail"));
                    user.add(String.valueOf(rs.getInt("credits")));
                    user.add(String.valueOf(rs.getInt("role")));
                    user.add(String.valueOf(rs.getInt("enabled")));
                    users.add(user);
            }


            rs.close();
            ps.close();
            return users;

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
    public List<List<String>> getAllUsers(){
        String sql = "SELECT DISTINCT (id),users.username, enabled, name, lastname, mail, credits, role  FROM users INNER JOIN user_roles ON users.username = user_roles.username";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ResultSet rs =ps.executeQuery();
            List<List<String>> users = new ArrayList<>();

            while (rs.next()) {
                    ArrayList<String> user = new ArrayList<>();
                    user.add(String.valueOf(rs.getInt("id")));
                    user.add(rs.getString("username"));
                    user.add(rs.getString("name"));
                    user.add(rs.getString("lastname"));
                    user.add(rs.getString("mail"));
                    user.add(String.valueOf(rs.getInt("credits")));
                    user.add(String.valueOf(rs.getInt("role")));
                    user.add(String.valueOf(rs.getInt("enabled")));
                    users.add(user);
            }


            rs.close();
            ps.close();
            return users;

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
    public List<List<String>> getBannedUsers(){
        String sql = "SELECT DISTINCT (id),users.username, enabled, name, lastname, mail, credits, role FROM users INNER JOIN user_roles ON users.username = user_roles.username WHERE users.enabled = 0";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ResultSet rs =ps.executeQuery();
            List<List<String>> users = new ArrayList<>();

            while (rs.next()) {
                    ArrayList<String> user = new ArrayList<>();
                    user.add(String.valueOf(rs.getInt("id")));
                    user.add(rs.getString("username"));
                    user.add(rs.getString("name"));
                    user.add(rs.getString("lastname"));
                    user.add(rs.getString("mail"));
                    user.add(String.valueOf(rs.getInt("credits")));
                    user.add(String.valueOf(rs.getInt("role")));
                    user.add(String.valueOf(rs.getInt("enabled")));
                    users.add(user);
            }


            rs.close();
            ps.close();
            return users;

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
    public List<List<String>> getZeroCreditsUsers(){
        String sql = "SELECT DISTINCT (users.id),users.username, enabled, name, lastname, mail, credits, role  FROM users INNER JOIN user_roles ON users.username = user_roles.username WHERE users.enabled = 1 AND users.credits = 0 AND user_roles.role=2";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ResultSet rs =ps.executeQuery();
            List<List<String>> users = new ArrayList<>();

            while (rs.next()) {
                    ArrayList<String> user = new ArrayList<>();
                    user.add(String.valueOf(rs.getInt("id")));
                    user.add(rs.getString("username"));
                    user.add(rs.getString("name"));
                    user.add(rs.getString("lastname"));
                    user.add(rs.getString("mail"));
                    user.add(String.valueOf(rs.getInt("credits")));
                    user.add(String.valueOf(rs.getInt("role")));
                    user.add(String.valueOf(rs.getInt("enabled")));
                    users.add(user);
            }


            rs.close();
            ps.close();
            return users;

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
    public boolean setCredits(int id, int credits){
        String sql = "UPDATE users " +
                      "SET credits = ? WHERE id = ? LIMIT 1";
        Connection conn = null;
        int result = 0;
        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, credits);
                ps.setInt(2, id);
                result = ps.executeUpdate();
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
        return (result != 0);
    }
}
