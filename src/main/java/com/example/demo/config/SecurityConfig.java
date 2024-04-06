package com.example.demo.config;

import com.example.demo.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/home/login")
                        .loginProcessingUrl("/home/login")
                        .usernameParameter("usernameLogin")
                        .passwordParameter("passwordLogin")
                        .defaultSuccessUrl("/admin", true)
                        .successHandler((request, response, authentication) -> { // Thêm xử lý cho trường hợp thành công
                            for (GrantedAuthority authority : authentication.getAuthorities()) {
                                if (authority.getAuthority().equals("USER")) {
                                    response.sendRedirect("/home"); // Chuyển hướng đến "/home"
                                }else {
                                    response.sendRedirect("/admin"); // Chuyển hướng đến "/admin" cho ADMIN
                                }
                            }
                        }))
                .logout(logout -> logout
                        .logoutUrl("/admin-logout")
                        .logoutSuccessUrl("/home"));
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
