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
public class CaptureDB extends Database{
     public int insertCapture(Capture c){
        
        String sql = "INSERT INTO capture (datetime, ra, declination, hBinning, vBinning, temperature, frameType, x, y, width, height, focusPosition, exposureTime, filePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        int id;
        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setString(1, c.getDatetime());
                ps.setString(2, c.getRa());
                ps.setString(3, c.getDec());
                ps.setString(4, c.gethBinning());
                ps.setString(5, c.getvBinning());
                ps.setString(6, c.getTemperature());
                ps.setString(7, c.getFrameType());
                ps.setString(8, c.getX());
                ps.setString(9, c.getY());
                ps.setString(10, c.getWidth());
                ps.setString(11, c.getHeight());
                ps.setString(12, c.getFocusPosition());
                ps.setString(13, c.getExposureTime());
                ps.setString(14, c.getFilePath());
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
    public int asociateCaptureToUser(int id_user, int id_capture){
        String sql = "INSERT INTO user_capture (id_user, id_capture) VALUES (?, ?)";
        
        Connection conn = null;
        int id;
        
        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, id_user);
                ps.setInt(2, id_capture);
                id = ps.executeUpdate();
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
    public void removeCapture(int id_capture){
        
        String sql = "UPDATE user_capture " +
                      "SET enabled = 0 WHERE id_capture = ?";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, id_capture);
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
    public void removeLastCapture(){
        
        String sql1 = "DELETE FROM user_capture ORDER BY id DESC LIMIT 1";
        String sql2 = "DELETE FROM capture ORDER BY id DESC LIMIT 1";
        
        Connection conn = null;

        try {
                conn = dataSource.getConnection();

                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql1);
                ps.executeUpdate();
                ps.close();

                ps = (PreparedStatement) conn.prepareStatement(sql2);
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
    public List<Capture> getCaptures(int id_user){
        String sql = "SELECT * FROM capture INNER JOIN user_capture ON capture.id = user_capture.id_capture WHERE user_capture.id_user = ? AND user_capture.enabled = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id_user);

            ResultSet rs =ps.executeQuery();
            Capture capture = null;
            List<Capture> captures = new ArrayList<>();
            while (rs.next()) {
                capture = new Capture(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("datetime"),
                    rs.getString("ra"),
                    rs.getString("declination"),
                    rs.getString("hBinning"),
                    rs.getString("vBinning"),
                    rs.getString("temperature"),
                    rs.getString("frameType"),
                    rs.getString("x"),
                    rs.getString("y"),
                    rs.getString("width"),
                    rs.getString("height"),
                    rs.getString("focusPosition"),
                    rs.getString("exposureTime"),
                    rs.getString("filePath")
                );
                captures.add(capture);
            }


            rs.close();
            ps.close();
            return captures;

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
     public List<List<String>> getCapturesAsList(int id_user){
        String sql = "SELECT * FROM capture INNER JOIN user_capture ON capture.id = user_capture.id_capture WHERE user_capture.id_user = ? AND user_capture.enabled = 1 ORDER BY capture.id DESC";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id_user);

            ResultSet rs =ps.executeQuery();
           
            List<List<String>> captures = new ArrayList<>();
            
            while (rs.next()) {
                List<String> capture = new ArrayList<>();
                capture.add(String.valueOf(rs.getInt("id")));
                capture.add(rs.getString("datetime"));
                capture.add(rs.getString("ra"));
                capture.add(rs.getString("declination"));
                capture.add(rs.getString("hBinning"));
                capture.add(rs.getString("vBinning"));
                capture.add(rs.getString("temperature"));
                capture.add(rs.getString("frameType"));
                capture.add(rs.getString("x"));
                capture.add(rs.getString("y"));
                capture.add(rs.getString("width"));
                capture.add(rs.getString("height"));
                capture.add(rs.getString("focusPosition"));
                capture.add(rs.getString("exposureTime"));
                capture.add(rs.getString("filePath"));
                
                captures.add(capture);
            }


            rs.close();
            ps.close();
            return captures;

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
