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
public class CalendarDB extends Database{
    
    
    public List<String> getShifts(){
        String sql = "SELECT datetime FROM shifts WHERE enabled = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            
            List<String> shifts = new ArrayList<>();
            
            while (rs.next()) {
                shifts.add(rs.getString("datetime").substring(0, 16));              
            }
            rs.close();
            ps.close();
            return shifts;

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
    
    public List<String> getShifts(String from){
        String sql = "SELECT datetime FROM shifts WHERE enabled = 1 AND datetime > ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            
            ps.setString(1, from);
            
            ResultSet rs = ps.executeQuery();
            
            List<String> shifts = new ArrayList<>();
            
            while (rs.next()) {
                shifts.add(rs.getString("datetime").substring(0, 16));              
            }
            rs.close();
            ps.close();
            return shifts;

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
