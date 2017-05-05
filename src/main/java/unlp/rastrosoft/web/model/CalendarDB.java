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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
    public ArrayList<String> getCurrentShift(){
        
        //String sql = "SELECT id_user FROM shifts WHERE (enabled=1 AND (  now() BETWEEN datetime AND ADDTIME(datetime, '01:00:00')))";
        String sql = "SELECT id_user, (TIME(SUBTIME( (ADDTIME(datetime, '01:00:00')), (CURTIME()))))as timeleft, live_key, public FROM shifts WHERE (enabled=1 AND (  now() BETWEEN datetime AND ADDTIME(datetime, '01:00:00')))";
        Connection conn = null;
        int id_user=-1;
        String timeleft=null;
        String live_key = "-1";
        String public_val = "0";
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                id_user     = rs.getInt("id_user");  
                timeleft    = rs.getString("timeleft");
                live_key    = rs.getString("live_key");
                public_val    = rs.getString("public");
            }
            rs.close();
            ps.close();
            ArrayList<String> result = new ArrayList<>();
            result.add(String.valueOf(id_user));
            result.add(timeleft);
            result.add(live_key);
            result.add(public_val);
            return result;
            
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
    
    public int insertShift(int id_user, String datetime, String enabled, String live_key, String public_val){
        String sql = "INSERT INTO shifts " +
                      "(id_user, datetime, enabled, live_key, public ) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;

        int shift_id = -1;
        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, id_user);
                ps.setString(2, datetime);
                ps.setString(3, enabled);
                ps.setString(4, live_key);
                ps.setString(5, public_val);
                ps.executeUpdate();
                shift_id = (int) ps.getLastInsertID();
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
        return shift_id;
    }
    
    public List<List<String>> getAllShifts(String from){
        String sql = "SELECT * FROM shifts WHERE datetime >= ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            
            ps.setString(1, from);
            
            ResultSet rs = ps.executeQuery();
            
            List<List<String>> shifts = new ArrayList<>();
            
            while (rs.next()) {   
                
                int public_val = rs.getInt("public");
                String live_key = rs.getString("live_key");
                if (public_val == 0){
                    live_key = "-1";
                }
                            
                Calendar shift = new Calendar(rs.getInt("id"), rs.getInt("id_user"), rs.getString("datetime").substring(0, 16), rs.getInt("enabled"), live_key, public_val );
                shifts.add(shift.getListOfStrings());
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
