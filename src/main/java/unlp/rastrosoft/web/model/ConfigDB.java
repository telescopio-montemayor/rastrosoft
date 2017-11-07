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
public class ConfigDB extends Database{
    
    public String getPath(){
        String sql = "SELECT path FROM config_data WHERE id = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("path");
            }
            rs.close();
            ps.close();
            return null;
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
    public String getIndiServerLocation(){
        String sql = "SELECT indi_server_location FROM config_data WHERE id = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("indi_server_location");
            }
            rs.close();
            ps.close();
            return null;
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
    public int getIndiServerPort(){
        String sql = "SELECT indi_server_port FROM config_data WHERE id = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("indi_server_port");
            }
            rs.close();
            ps.close();
            return -1;
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
    public String getHostWww(){
        String sql = "SELECT host_www FROM config_data WHERE id = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("host_www");
            }
            rs.close();
            ps.close();
            return null;
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
