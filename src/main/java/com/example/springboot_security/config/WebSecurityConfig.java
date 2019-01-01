package com.example.springboot_security.config;

import com.example.springboot_security.util.MD5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * Created by yangyibo on 17/1/18.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsService customUserService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        md5加密方式
//        auth.userDetailsService(customUserService)
//                .passwordEncoder(new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return MD5Util.encode((String) rawPassword);
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return encodedPassword.equals(MD5Util.encode((String) rawPassword));
//            }
//        }); //user Details Service验证

        //        密码编码器
        PasswordEncoder passwordEncoder=new Pbkdf2PasswordEncoder();
        auth.userDetailsService(customUserService)
                .passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                     .antMatchers("/css/**", "/js/**","/images/**", "/webjars/**", "**/favicon.ico", "/index")
//                    .permitAll()
//                .and()
//                    .rememberMe().tokenValiditySeconds(86400).key("remember_me_key")
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .failureUrl("/login?error")
//                    .permitAll() //登录页面用户任意访问
//                .and()
//                    .logout()
//                    .permitAll(); //注销行为任意访问

        http.authorizeRequests()
                .antMatchers("/user").access("hasRole('USER') ")
                .antMatchers("/admin").access("hasRole('ADMIN')   &&isFullyAuthenticated()" )
                .and().rememberMe()
                .and().formLogin().loginPage("/login")
                                  .failureUrl("/login?error")
                                  .permitAll(); //登录页面用户任意访问



    }


}

