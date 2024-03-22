package wendy.study.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
// 또는 @Configuration
public class CustomSecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated();
//        http.httpBasic();
//        http.httpBasic().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//        //spring security default : 인증정보 session 사용
//        //session을 사용하지 않겠다고 선언하면 매 요청마다 인증받아야 함
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.formLogin();
//        http.exceptionHandling().authenticationEntryPoint(
//            (request, response, authException) -> {
//                System.out.println("custom Entry Point");
//        });
//        return http.build();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated();
//        http.formLogin();
//        return http.build();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated();
//        http.httpBasic();
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain corsFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest().authenticated();
        http.cors().configurationSource(corsConfigSource());

        return http.build();
    }

    private CorsConfigurationSource corsConfigSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
//        config.setAllowCredentials(true);
//        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
