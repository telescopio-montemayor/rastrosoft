/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author ip300
 */
public class Database {
    public MysqlDataSource dataSource;

    public MysqlDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(MysqlDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Database() {
        this.dataSource = null;
    }
    
    public void connect (){
        if(this.dataSource == null ){
            dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("");
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("rastrosoft");
        }             
                    
    }

    
}
