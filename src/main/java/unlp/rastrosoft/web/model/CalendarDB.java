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
    public boolean checkAvailableShift( String datetime ){
        String sql = "SELECT datetime FROM shifts WHERE enabled = 1 AND datetime = ? LIMIT 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, datetime);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return false;
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    return false;
                }
            }
        }
        return true;
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
        String sql = "SELECT * FROM shifts WHERE datetime >= ? ORDER BY datetime ASC";
        Connection conn = null;
        
        UserDB userDB = new UserDB();
        userDB.connect();
        int current_user_id = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {            
            User user = userDB.getUser(authentication.getName());
            current_user_id = user.getUserId();
        }
        
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
                int user_id = rs.getInt("id_user");
                if ( rs.getInt("id_user") == current_user_id){
                    user_id = 0;
                }
                Calendar shift = new Calendar(rs.getInt("id"), user_id , rs.getString("datetime").substring(0, 16), rs.getInt("enabled"), live_key, public_val );
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
    public List<List<String>> getAllShiftsWithName(String from){
        String sql = "SELECT * FROM shifts WHERE datetime >= ? ORDER BY datetime ASC";
        Connection conn = null;
        
        UserDB userDB = new UserDB();
        userDB.connect();
        int current_user_id = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {            
            User user = userDB.getUser(authentication.getName());
            current_user_id = user.getUserId();
        }
        
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
                int user_id = rs.getInt("id_user");
                String name = "";
                if ( user_id == current_user_id){
                    name = "0";
                }else{
                    name = userDB.getUser(user_id).getName();                    
                }
                
                ArrayList<String> shift_a = new ArrayList<>();
                shift_a.add(String.valueOf(rs.getInt("id")));
                shift_a.add(String.valueOf(name));
                shift_a.add(rs.getString("datetime").substring(0, 16));
                shift_a.add(String.valueOf(rs.getInt("enabled")));
                shift_a.add(live_key);
                shift_a.add(String.valueOf(public_val));                
                shifts.add(shift_a);
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
    public List<List<String>> getModerationShifts(String from, int state){
        String sql = "SELECT * FROM shifts WHERE datetime >= ? AND enabled = ? ORDER BY datetime ASC";
        Connection conn = null;
        
        UserDB userDB = new UserDB();
        userDB.connect();
        
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            
            ps.setString(1, from);
            ps.setInt(2, state);
            
            ResultSet rs = ps.executeQuery();
            
            List<List<String>> shifts = new ArrayList<>();
            
            while (rs.next()) {   
                int user_id = rs.getInt("id_user");
                int public_val = rs.getInt("public");
                String live_key = rs.getString("live_key");
                if (public_val == 0){
                    live_key = "-1";
                }
                User user = userDB.getUser(user_id);
                
                ArrayList<String> shift_a = new ArrayList<>();
                shift_a.add(String.valueOf(rs.getInt("id")));                
                shift_a.add(user.getUsername());
                shift_a.add(user.getName());
                shift_a.add(user.getLastname());
                shift_a.add(user.getMail());
                shift_a.add(String.valueOf(user_id));
                shift_a.add(rs.getString("datetime").substring(0, 16));
                shift_a.add(String.valueOf(rs.getInt("enabled")));
                shift_a.add(live_key);
                shift_a.add(String.valueOf(public_val));
                
                shifts.add(shift_a);
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
    public void acceptShift(int shift_id){
        String sql = "UPDATE shifts " +
                      "SET enabled = 1 WHERE id = ? LIMIT 1";
        Connection conn = null;
        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, shift_id);
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
    public void rejectShift(int shift_id){
        String sql = "UPDATE shifts " +
                      "SET enabled = 0 WHERE id = ? LIMIT 1";
        Connection conn = null;
        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, shift_id);
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
    public void setToPendingShift(int shift_id){
        String sql = "UPDATE shifts " +
                      "SET enabled = 2 WHERE id = ? LIMIT 1";
        Connection conn = null;
        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

                ps.setInt(1, shift_id);
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
