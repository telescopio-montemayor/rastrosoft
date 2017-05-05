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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
			"select username,password, enabled from users where username=?").passwordEncoder(new BCryptPasswordEncoder())
		.authoritiesByUsernameQuery(
			"select username, role_name from user_roles INNER JOIN roles ON ( user_roles.role = roles.role ) where username=?");
        
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/resources/**").permitAll()
            .antMatchers("/live").permitAll()
                .antMatchers("/refreshValues").permitAll()
                .antMatchers("/actions").permitAll()
                .antMatchers("/getChat").permitAll()
                .antMatchers("/checkLive").permitAll()
                .antMatchers("/captures/**").permitAll()
                
            .anyRequest().authenticated()
            .and()
        .formLogin().loginPage("/login")
        .usernameParameter("ssoId").passwordParameter("password")
        .and().csrf();
    }
    
}