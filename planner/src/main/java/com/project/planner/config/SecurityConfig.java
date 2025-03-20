package com.project.planner.config;

import com.project.planner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // Web 기반 Security
@EnableGlobalMethodSecurity(prePostEnabled = true) // Method 기반 Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    // BCryptPasswordEncoder : Spring Security 비밀번호 암호화 객체
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "img/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    // HttpSecurity : Http 요청에 대한 보안 구성
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable(); csrf 토큰 임시 비활성화

        http.sessionManagement()
                .maximumSessions(1) // 최대 허용 가능 세션 수
                .maxSessionsPreventsLogin(false) // 동시로그인 차단
                .expiredUrl("/login"); // 세션이 만료된 경우 이동 할 페이지
        http.authorizeHttpRequests() // 권한 설정
                .antMatchers("/","/registry","/login", "/css/**").permitAll()
                // 특정 경로에 대한 접근 권한 설정, 모든 사용자 접근간으
                .antMatchers("/user/**", "/myPlanner/**", "/map/**", "/mou/**").authenticated()
                // 일반사용자 접근 가능 (로그인한 사용자만 접근 가능)
                .antMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
                // 매니저, 관리자 접근 가능
                .antMatchers("/admin/**").hasRole("ADMIN"); // 관리자만 접근 가능
        http.formLogin().loginPage("/login")
                .failureForwardUrl("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/user/home", true)
                .failureUrl("/login/error");
        // 인증 필요시 로그인 페이지와 로그인 성공시 리다이랙션 경로 지정
        http.exceptionHandling().accessDeniedPage("/forbidden");
        // 인증된 사용자이지만 인가되지 않은 경로에 접근시 리다이랙션 시킬 url 지정
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true); // 로그아웃 성공 시 이동페이지 세션 초기화
        http.cors().configurationSource(corsConfigurationSource());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        // 사용자 인증을 구성하는 객체 서비스를 통해 정보를 로드하고 인증 처리
    }

}
