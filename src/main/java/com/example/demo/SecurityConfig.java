package com.example.demo;

import com.example.demo.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable()).authorizeHttpRequests(auth->auth

                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/**").permitAll()
                .anyRequest().authenticated())

                .formLogin(login->login.loginPage("/logon").loginProcessingUrl("/logon")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin", true))
                .logout(logout->logout.logoutUrl("/admin-logout").logoutSuccessUrl("/logon"));
        return http.build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        // mã hóa password
        return new BCryptPasswordEncoder();
    }

    // cấp quyền cho đọc dc css
    @Bean
    WebSecurityCustomizer webSecurityCustomizer(){
            return (web)->web.ignoring().requestMatchers("/manager/**","/admin/manager/**");
    }

}
