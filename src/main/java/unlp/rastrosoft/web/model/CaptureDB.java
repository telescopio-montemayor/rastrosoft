/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

import com.mysql.cj.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

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
    public void removeCapture(String id_capture){
        
        String sql = "UPDATE user_capture " +
                      "SET enabled = 0 WHERE id_capture = ?";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setString(1, id_capture);
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
