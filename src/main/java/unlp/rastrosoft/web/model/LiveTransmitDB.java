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
public class LiveTransmitDB extends Database{
    
    public boolean startLiveTransmit(){
        String sql = "UPDATE live_transmit SET enabled = 1 WHERE id = 1";
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
     public boolean stopLiveTransmit(){
        String sql = "UPDATE live_transmit SET enabled = 0 WHERE id = 1";
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
    public boolean isOnLive(){
        String sql = "SELECT enabled FROM live_transmit WHERE id = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            Boolean  live = false;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("enabled") == 1){
                    live = true;
                }else{
                    live = false;
                }
            }
            rs.close();
            ps.close();
            return live;

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
