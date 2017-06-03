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
public class QuickAccessTargetDB extends Database{
    
    
     public List<List<String>> getTargets(){
        String sql = "SELECT * FROM messier ORDER BY id ASC;";     
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
           
            List<List<String>> targets = new ArrayList<>();
            
            while (rs.next()) {
                List<String> target = new ArrayList<>();
                target.add(String.valueOf(rs.getInt("id")));
                target.add(rs.getString("RA"));
                target.add(rs.getString("DEC"));
                target.add(String.valueOf(rs.getInt("NGC")));
                
                targets.add(target);
            }


            rs.close();
            ps.close();
            return targets;

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
