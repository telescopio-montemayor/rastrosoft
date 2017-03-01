/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.config;

/**
 *
 * @author ip300
 */
import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import unlp.rastrosoft.web.model.Database;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
        MysqlDataSource dataSource;
        Database db = new Database();
        db.connect();
        dataSource = db.getDataSource();
        
        auth.jdbcAuthentication().dataSource((DataSource)dataSource)
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from user_roles where username=?");
        
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/login");
//    }
}