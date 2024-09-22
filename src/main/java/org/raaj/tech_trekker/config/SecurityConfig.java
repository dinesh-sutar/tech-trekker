package org.raaj.tech_trekker.config;

import org.raaj.tech_trekker.repository.WriterInfoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// import org.springframework.util.AntPathMatcher;

@Configuration
public class SecurityConfig {
        // @Bean
        // UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // var user1 = User.builder()
        // .username("raaj")
        // .password(passwordEncoder.encode("0922"))
        // .roles("")
        // .build();

        // return new InMemoryUserDetailsManager(user1);
        // }
        @Bean
        UserDetailsService userDetailsService(WriterInfoRepository repository) {
                return username -> {
                        var writter = repository.findByEmail(username)
                                        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
                        return User.builder()
                                        .username(username).password(writter.getPassword())
                                        .roles("")
                                        .build();
                };
        }

        // Authorisation
        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(
                                request -> request.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                                .requestMatchers("/", "/tech-trekker/*").permitAll()
                                                .requestMatchers("/blog/image/*").permitAll()
                                                .anyRequest().authenticated());

                // http.formLogin(Customizer.withDefaults());

                http.formLogin(form -> form.loginPage("/login-page")
                                .loginProcessingUrl("/login").permitAll());

                http.csrf(csrf -> csrf.ignoringRequestMatchers("/tech-trekker/signup", "/tech-trekker/view-all-search",
                                "/login"));

                http.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                                .logoutSuccessUrl("/").deleteCookies());

                // http.logout(logout -> logout.logoutUrl("/logout"));

                return http.build();
        }

        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder(12);
        }
}
