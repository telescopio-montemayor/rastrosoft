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
     public int insertStep(int id_sequence, int number, String ra, String declination, String exposureTime, String hBinning, String vBinning, String frameType, String x, String y, String width, String height, String focusPosition, int quantity, int delay){
        
        String sql = "INSERT INTO step (id_sequence, number, ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition, quantity, delay ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        int id;
        
        if (ra == null           || ra.equals("") )             ra                  =   "-";
        if (declination == null  || declination.equals("") )    declination         =   "-";
        if (exposureTime == null || exposureTime.equals("") )   exposureTime        =   "1";
        if (hBinning == null     || hBinning.equals("") )       hBinning            =   "-";
        if (vBinning == null     || vBinning.equals("") )       vBinning            =   "-";
        if (frameType == null    || frameType.equals("") )      frameType           =   "-";
        if (x == null            || x.equals("") )              x                   =   "-";
        if (y == null            || y.equals("") )              y                   =   "-";
        if (width == null        || width.equals("") )          width               =   "-";
        if (height == null       || height.equals("") )         height              =   "-";
        if (focusPosition == null|| focusPosition.equals("") )  focusPosition       =   "-";
        
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
                ps.setInt(15, delay);
                
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
    
    public void removeStep(int id_step, int id_sequence){
        
//        String sql = "UPDATE step " +
//                      "SET state = -1 WHERE id = ?";
        String sqlGet = "SELECT number " +
                      "FROM step WHERE id = ? LIMIT 1";
        int number = 0;
        String sqlGetMax = "SELECT MAX(number) as number " +
                      "FROM step WHERE id_sequence = ? LIMIT 1";
        int max_number = 0;
        String sqlChangePrevious = "UPDATE step " +
                      "SET number = number-1 WHERE number = ? AND id_sequence = ?";
        int next = 0;
        String sql = "DELETE FROM step " +
                      "WHERE id = ?";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sqlGet);

                ps.setInt(1, id_step);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                   number = rs.getInt("number"); 
                }
                ps = (PreparedStatement) conn.prepareStatement(sqlGetMax);
                ps.setInt(1, id_sequence); 
                rs = ps.executeQuery();
                while (rs.next()) {
                   max_number = rs.getInt("number"); 
                }
                next = number;
                ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(1, id_step);
                ps.executeUpdate();
                while(next <= max_number){
                    ps = (PreparedStatement) conn.prepareStatement(sqlChangePrevious);                    
                    ps.setInt(1, next);
                    ps.setInt(2, id_sequence);
                    ps.executeUpdate();
                    next = next + 1;
                    ps = (PreparedStatement) conn.prepareStatement(sqlGetMax);
                    ps.setInt(1, id_sequence); 
                    rs = ps.executeQuery();
                    while (rs.next()) {
                       max_number = rs.getInt("number"); 
                    }
                }
                
                
                
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
    
    public void goUpStep(int id_step, int id_sequence){
        
        String sqlGet = "SELECT number " +
                      "FROM step WHERE id = ? LIMIT 1";
        int number = 0;
        String sql = "UPDATE step " +
                      "SET number = ? WHERE id = ?";
        
        String sqlChangePrevious = "UPDATE step " +
                      "SET number = ? WHERE number = ? AND id_sequence = ?";
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sqlGet);

                ps.setInt(1, id_step);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                   number = rs.getInt("number"); 
                }
                if (number>1){
                    ps = (PreparedStatement) conn.prepareStatement(sqlChangePrevious);
                    ps.setInt(1, number);
                    ps.setInt(2, number-1);
                    ps.setInt(3, id_sequence);
                    ps.executeUpdate();
                    
                    ps = (PreparedStatement) conn.prepareStatement(sql);
                    ps.setInt(1, number-1);
                    ps.setInt(2, id_step);
                    ps.executeUpdate();
                    
                }
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
    public void goDownStep(int id_step, int id_sequence){
        
        String sqlGet = "SELECT number " +
                      "FROM step WHERE id = ? LIMIT 1";
        int number = 0;
        String sql = "UPDATE step " +
                      "SET number = ? WHERE id = ?";
        
        String sqlChangePrevious = "UPDATE step " +
                      "SET number = ? WHERE number = ? AND id_sequence = ?";
        
        String sqlGetMax = "SELECT MAX(number) as number " +
                      "FROM step WHERE id_sequence = ? LIMIT 1";
        int max_number = 0;
        
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sqlGet);

                ps.setInt(1, id_step);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                   number = rs.getInt("number"); 
                }
                ps = (PreparedStatement) conn.prepareStatement(sqlGetMax);
                ps.setInt(1, id_sequence);
                rs = ps.executeQuery();
                while (rs.next()) {
                   max_number = rs.getInt("number"); 
                }
                if (number < max_number){
                    ps = (PreparedStatement) conn.prepareStatement(sqlChangePrevious);
                    ps.setInt(1, number);
                    ps.setInt(2, number+1);
                    ps.setInt(3, id_sequence);
                    ps.executeUpdate();
                    
                    ps = (PreparedStatement) conn.prepareStatement(sql);
                    ps.setInt(1, number+1);
                    ps.setInt(2, id_step);
                    ps.executeUpdate();
                    
                }
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
        String sql = "SELECT id, id_sequence, number, ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition, quantity, delay, state FROM step WHERE id_sequence = ? AND state <> -1 ORDER BY number ASC";
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
                step.add(String.valueOf(rs.getInt("quantity")));
                step.add(String.valueOf(rs.getInt("delay")));
                step.add(String.valueOf(rs.getInt("state")));
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
     public Step getStep(int id){
        String sql = "SELECT id, id_sequence, number, ra, declination, exposureTime, hBinning, vBinning, frameType, x, y, width, height, focusPosition, quantity, delay, state FROM step WHERE id = ? AND state <> -1 ORDER BY number ASC LIMIT 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =ps.executeQuery();
            Step current_step = null;
            while (rs.next()) {
                
                current_step = new Step (
                        rs.getInt("id"), rs.getInt("id_sequence"),rs.getInt("number"), rs.getString("ra"), 
                        rs.getString("declination"), rs.getString("exposureTime"), rs.getString("hBinning"), 
                        rs.getString("vBinning"), rs.getString("frameType"), rs.getString("x"), rs.getString("y"),
                        rs.getString("width"), rs.getString("height"), rs.getString("focusPosition"), rs.getInt("quantity"),
                        rs.getInt("delay"), rs.getInt("state"));
            }
            
            rs.close();
            ps.close();
            return current_step;

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
    
    public List<Integer> getSteps(int id_sequence){
        String sql = "SELECT id FROM step WHERE id_sequence = ? AND state <> -1 ORDER BY number ASC";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ps.setInt(1, id_sequence);

            ResultSet rs =ps.executeQuery();
           
            List<Integer> steps = new ArrayList<>();
            
            while (rs.next()) {
                
                steps.add(rs.getInt("id"));
               
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
   
    /**
    * Changes the state of the Step.
    *
    * @param id_step the id of the Step.
    * @param id_sequence the id of the Sequence that contains the Step.
    * @param state the state can be 0 = Inactive, 1 = In progress, 2 = Completed, 3 = Interrupted.
    */
    public void changeState(int id_step, int id_sequence, int state){
        
        String sql = "UPDATE step " +
                      "SET state = ? WHERE id = ? AND id_sequence = ? LIMIT 1";
        
        Connection conn = null;

        try {
                conn = dataSource.getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                
                ps.setInt(1, state);
                ps.setInt(2, id_step);
                ps.setInt(3, id_sequence);
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
