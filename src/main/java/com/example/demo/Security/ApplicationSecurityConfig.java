package com.example.demo.Security;

import static com.example.demo.Security.ApplicationUserRole.ADMIN;
import static com.example.demo.Security.ApplicationUserRole.ADMINTRAINEE;
import static com.example.demo.Security.ApplicationUserRole.STUDENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
            .antMatchers("/api/**").hasAuthority(STUDENT.getRole())
            .anyRequest()
            .authenticated()
        .and()
            .formLogin();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annasmithUser = User.builder()
            .username("annasmith")
            .password(passwordEncoder.encode("password"))
            .authorities(STUDENT.getGrantedAuthorities())
            .build();

        UserDetails lindaUser = User.builder()
            .username("linda")
            .password(passwordEncoder.encode("password"))
            .authorities(ADMIN.getGrantedAuthorities())
            .build();

        UserDetails tomUser = User.builder()
            .username("tom")
            .password(passwordEncoder.encode("password"))
            .authorities(ADMINTRAINEE.getGrantedAuthorities())
            .build();

        return new InMemoryUserDetailsManager(
                annasmithUser, lindaUser, tomUser
        );
    }

}

