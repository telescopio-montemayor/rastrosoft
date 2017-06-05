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
public class SequenceDB extends Database{
     public int insertSequence(int id_user, String name){
        
        String sql = "INSERT INTO sequence (id_user, name, enabled) VALUES (?, ?, ?)";
        
        Connection conn = null;
        int id;
        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, id_user);
                ps.setString(2, name);
                ps.setInt(3, 1);
                ps.executeUpdate();
                id = (int) ps.getLastInsertID();
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
        return id;
    }
    
    public void removeSequence(int id_sequence, int id_user){
        
        String sql = "UPDATE sequence " +
                      "SET enabled = 0 WHERE id = ? AND id_user = ?";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, id_sequence);
                ps.setInt(2, id_user);
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
    public void modifySequence(int id_sequence, int id_user, String name){
        
        String sql = "UPDATE sequence " +
                      "SET name = ? WHERE id = ? AND id_user = ?";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                
                ps.setString(1, name);
                ps.setInt(2, id_sequence);
                ps.setInt(3, id_user);
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

    public List<String> getSequences(int id_user){
        String sql = "SELECT id FROM sequence WHERE id_user = ? AND enabled = 1 ORDER BY id ASC";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id_user);

            ResultSet rs =ps.executeQuery();
           
            List<String> sequences = new ArrayList<>();
            
            while (rs.next()) {
                sequences.add(String.valueOf(rs.getInt("id")));
            }


            rs.close();
            ps.close();
            return sequences;

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
    public List<List<String>> getSequencesAsList(int id_user){
        String sql = "SELECT id, name FROM sequence WHERE id_user = ? AND enabled = 1 ORDER BY id ASC";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id_user);

            ResultSet rs =ps.executeQuery();
           
            List<List<String>> sequences = new ArrayList<>();
            
            while (rs.next()) {
                List<String> sequence = new ArrayList<>();
                sequence.add(String.valueOf(rs.getInt("id")));
                sequence.add(rs.getString("name"));
                sequences.add(sequence);
            }


            rs.close();
            ps.close();
            return sequences;

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
    public String getSequence(int id_user, int id){
        String sql = "SELECT name FROM sequence WHERE id_user = ? AND id = ? LIMIT 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id_user);
            ps.setInt(2, id);

            ResultSet rs =ps.executeQuery();
           
            String  sequence = "-1";
            
            while (rs.next()) {
                sequence = rs.getString("name");
            }


            rs.close();
            ps.close();
            return sequence;

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
    public int getSequence(int id_user, String name){
        String sql = "SELECT id FROM sequence WHERE id_user = ? AND name = ? LIMIT 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id_user);
            ps.setString(2, name);

            ResultSet rs =ps.executeQuery();
           
            int sequence = -1;
            
            while (rs.next()) {
                sequence = rs.getInt("id");
            }


            rs.close();
            ps.close();
            return sequence;

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
    public int getUserId(int id_sequence){
        String sql = "SELECT id_user FROM sequence WHERE id = ? LIMIT 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id_sequence);

            ResultSet rs = ps.executeQuery();
           
            int id_user = -1;
            
            while (rs.next()) {
                id_user = rs.getInt("id_user");
            }


            rs.close();
            ps.close();
            return id_user;

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
    public void resetSequence(int id_sequence, int id_user){
        String sql = "UPDATE step " +
                      "SET state = 0 WHERE id_sequence = ?";
        Connection conn = null;
        
        if (id_user == this.getUserId(id_sequence)){
            try {
                    conn = dataSource.getConnection();
                    PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);


                    ps.setInt(1, id_sequence);
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
}
