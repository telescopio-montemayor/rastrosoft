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
public class StepDB extends Database{
     public int insertStep(int id_sequence, int number, String ra, String declination, String exposureTime, String hBinning, String vBinning, String frameType, String x, String y, String width, String height, String focusPosition, int quantity){
        
        String sql = "INSERT INTO step (id_sequence, number, ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition, quantity ) VALUES (?, ?, ?. ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        int id;
        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, id_sequence);
                ps.setInt(2, number);
                ps.setString(3, ra);
                ps.setString(4, declination);
                ps.setString(5, exposureTime);
                ps.setString(6, hBinning);
                ps.setString(7, vBinning);
                ps.setString(8, frameType);
                ps.setString(9, x);
                ps.setString(10, y);
                ps.setString(11, width);
                ps.setString(12, height);
                ps.setString(13, focusPosition);
                ps.setInt(14, quantity);
                
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
    
    public void removeStep(int id_step){
        
        String sql = "UPDATE step " +
                      "SET state = -1 WHERE id = ?";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, id_step);
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
    

    public List<List<String>> getStepsAsList(int id_sequence){
        String sql = "SELECT id, id_sequence, number, ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition, quantity, state FROM step WHERE id_sequence = ? AND state <> -1 ORDER BY id ASC";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id_sequence);

            ResultSet rs =ps.executeQuery();
           
            List<List<String>> steps = new ArrayList<>();
            
            while (rs.next()) {
                List<String> step = new ArrayList<>();
                step.add(String.valueOf(rs.getInt("id")));
                step.add(String.valueOf(rs.getInt("id_sequence")));
                step.add(String.valueOf(rs.getInt("number")));
                step.add(rs.getString("ra"));
                step.add(rs.getString("declination"));
                step.add(rs.getString("exposureTime"));
                step.add(rs.getString("hBinning"));
                step.add(rs.getString("vBinning"));
                step.add(rs.getString("frameType"));
                step.add(rs.getString("x"));
                step.add(rs.getString("y"));
                step.add(rs.getString("width"));
                step.add(rs.getString("height"));
                step.add(rs.getString("focusPosition"));
                step.add(rs.getString("quantity"));
                step.add(rs.getString("state"));
                steps.add(step);
            }
            
            rs.close();
            ps.close();
            return steps;

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
    public int getSequenceId(int id_step){
        String sql = "SELECT id_sequence FROM step WHERE id = ? LIMIT 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id_step);

            ResultSet rs = ps.executeQuery();
           
            int id_sequence = -1;
            
            while (rs.next()) {
                id_sequence = rs.getInt("id_sequence");
            }
            
            rs.close();
            ps.close();
            return id_sequence;

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
