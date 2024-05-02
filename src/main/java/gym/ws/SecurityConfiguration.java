package gym.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/doc/swagger-ui/index.html").permitAll()
                .requestMatchers("/cliente/login").permitAll() // Permitir el acceso al endpoint de login sin token
                .anyRequest().authenticated()
            )
            .exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("{\"error\": \"Access Denied\", \"message\": \"" + accessDeniedException.getMessage() + "\"}");
            });
        return http.build();
    }
}

//
//
//
//return http.authorizeHttpRequests((auth) -> {
//	auth.requestMatchers("/").permitAll();
//	auth.requestMatchers("/favicon.ico").permitAll();
//	auth.anyRequest().authenticated();
//}).oauth2Login(withDefaults()).formLogin(withDefaults()).build();