package com.foxminded.aprihodko.task10.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.foxminded.aprihodko.task10.models.Role;
import com.foxminded.aprihodko.task10.services.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return userServiceImpl;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/anonymous*")
//                .anonymous().antMatchers("/user/**").hasRole("USER").antMatchers("/login*").permitAll().anyRequest()
//                .authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/home", true).failureUrl("/login.html?error=true")
//                .failureHandler(authenticationFailureHandler()).and().logout().logoutUrl("/perform_logout")
//                .deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler()).permitAll();
        http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().antMatchers("/login*").permitAll()
                .antMatchers("/registration/**").permitAll().antMatchers(HttpMethod.GET, "/home/**")
                .hasAnyRole(Role.ADMIN.name(), Role.USER.name()).antMatchers(HttpMethod.POST, "/users/**")
                .hasRole(Role.ADMIN.name()).antMatchers(HttpMethod.DELETE, "/users/**").hasRole(Role.ADMIN.name());
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
