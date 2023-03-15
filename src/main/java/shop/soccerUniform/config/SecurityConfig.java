package shop.soccerUniform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import shop.soccerUniform.login.LoginFail;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bcrypt() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/member/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .usernameParameter("loginId")
                .loginProcessingUrl("/login")
                .failureHandler(loginFail())
                .defaultSuccessUrl("/");

        return http.build();
    }

    @Bean
    public LoginFail loginFail() {
        return new LoginFail();
    }
}
