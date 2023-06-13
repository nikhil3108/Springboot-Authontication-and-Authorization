package com.nikhil.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class DemoSecurityConfig {


      //Add support for jdbc.. no more hardcoding
    @Bean
     public UserDetailsManager userDetailsManager(DataSource dataSource){
         JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
         jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
         jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
         return jdbcUserDetailsManager;
     }



//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails Nikhil= User.builder()
//                .username("Nikhil")
//                .password("{noop}Nikhil123")
//                .roles("Employee")
//                .build();
//        UserDetails Niraj= User.builder()
//                .username("Niraj")
//                .password("{noop}Niraj123")
//                .roles("Employee","Manager")
//                .build();
//        UserDetails Vishal= User.builder()
//                .username("Vishal")
//                .password("{noop}Vishal123")
//                .roles("Employee","Manager","Admin")
//                .build();
//        return new InMemoryUserDetailsManager(Nikhil,Niraj,Vishal);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(Configurer->
                                   Configurer
                                           .requestMatchers("/").hasRole("EMPLOYEE")
                                           .requestMatchers("/leaders/**").hasRole("MANAGER")
                                           .requestMatchers("/admin/**").hasRole("ADMIN")
                                           .anyRequest().authenticated())
                                           .formLogin(form->
                                               form.loginPage("/showMyLoginPage")
                                                   .loginProcessingUrl("/authenticateTheUser")
                                                   .permitAll()
                                           )
                                           .logout(logout->logout.permitAll())
                                           .exceptionHandling(Configurer->
                                                Configurer
                                                         .accessDeniedPage("/access-denied"));
        return http.build();
    }

}
