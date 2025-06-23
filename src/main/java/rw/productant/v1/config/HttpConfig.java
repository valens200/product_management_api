package rw.productant.v1.config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rw.productant.v1.common.dtos.CustomAutError;
import rw.productant.v1.config.jwt.JwtAuthentivationFilter;
import rw.productant.v1.user.services.UserSecurityDetailsService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // Lombok annotation for constructor injection
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enables Spring Security global method security
public class HttpConfig {

    // Fields for dependency injection
    private CustomAutError authError;
    private JwtAuthentivationFilter authFilter;
    private UserSecurityDetailsService userSecurityDetailsService;

    @Autowired
    public HttpConfig(CustomAutError customAutError, JwtAuthentivationFilter authFilter, UserSecurityDetailsService securityDetailsService){
        this.authError = customAutError;
        this.authFilter = authFilter;
        this.userSecurityDetailsService = securityDetailsService;
    }

    // Bean definition for security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(req -> req
                        .antMatchers("/api/v1/auth/**","/api/v1/users/admin/create").permitAll()
                        .antMatchers(
                                "/v2/api-docs",
                                "/configuration/ui",
                                "/swagger-resources/**",
                                "/configuration/security",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html",
                                "/webjars/**",
                                "/api/v1/auth/verify-account",
                                "/api/v1/auth/reset-password",
                                "/api/v1/files/**",
                                "/v1/api-docs/**",
                                "/swagger-ui/**",
                                "/api/product/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex.authenticationEntryPoint(authError))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    // Bean definition for BCryptPasswordEncoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Bean definition for UserDetailsService
    @Bean
    public UserDetailsService userDetailsService(){
        return userSecurityDetailsService::loadUserByUsername; // Return UserDetailsService implementation
    }
}
