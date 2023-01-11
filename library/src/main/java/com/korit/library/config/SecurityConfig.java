package com.korit.library.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();//ROLE_ADMIN,MANAGER 시큐리티는 ROLE_가있어야 관한이라고 인식한데 사용할떄 앞에 ROLE_은 떄서 사용한다 자료형같은거
        http.authorizeRequests()
                .antMatchers("/mypage/**", "/security/**")//인증을 거처야하는 요청 로그인 하지 않으면 로그인페이지로 보넴 시큐리티가 보넴 이경우 로그인 성공시 원래 요청을 날렸던곳 원레드려가던곳으로 이동한다4
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/account/login") // 로그인 페이지 get요청
                .loginProcessingUrl("/account/login") // 로그인 인증 post 요청
                .failureForwardUrl("/account/login/error") //로그인 실페시 보넬곳
                .successForwardUrl("/mypage")//로그인 성공시 무조건 마이페이지로 보냄
                .defaultSuccessUrl("/index");//로그인 성공시 어디로 보넬것인가

    }
}