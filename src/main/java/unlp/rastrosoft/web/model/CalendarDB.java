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
        String sql = "SELECT date FROM shifts WHERE enabled = 1";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ResultSet rs =ps.executeQuery();
            
            List<String> shifts = new ArrayList<>();

            if (rs.next()) {
                shifts.add(rs.getString("date"));
                System.out.println(rs.getString("date"));
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
