package project.aiport.aiportproject1.Configuration;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import project.aiport.aiportproject1.DAO.UsersRepository;
import project.aiport.aiportproject1.Entity.users;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, enabled " +
                        "FROM users " +
                        "WHERE email = ?"
        );

        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/confirm/*").permitAll()
                                .anyRequest().hasAnyRole("USER", "MODERATOR")

                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedPage("/access-denied"))
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/showMyLoginPage")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .loginProcessingUrl("/authenticateTheUser")
                                .successHandler((request, response, authentication) -> {
                                    String username = authentication.getName();
                                    HttpSession session = request.getSession();
                                    session.setAttribute("username", username);
                                    response.sendRedirect("/home");
                                })
                                .permitAll()

                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionManagement ->
                sessionManagement
                        .invalidSessionUrl("/showMyLoginPage")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl("/showMyLoginPage")
        );
        return http.build();
    }
}

