package com.nightfury.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(
                        config -> config
                                .requestMatchers("basic-auth/public").permitAll() // make "basic-auth/hello" available for everyone
                                .anyRequest().authenticated() // secure all other endpoints
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(fL ->
                        fL
                                .defaultSuccessUrl("/basic-auth/private",true)
                                .usernameParameter("user")
                                .passwordParameter("pwd")
                )
                .rememberMe( rM ->
                        rM
                                .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
                                .key("wolverine")
                                .rememberMeParameter("remember-me")
                )
                .logout(lg ->
                        lg
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .logoutSuccessUrl("/login")
                                .permitAll()
                );

        return httpSecurity.build();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
        PasswordEncoder passwordEncoder = noOpEncoder();
        UserDetails user1 = User.builder()
                .username("admin1")
                .password(passwordEncoder.encode("1234"))
                .authorities("admin")
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.builder()
                .username("admin2")
                .password(passwordEncoder.encode("1234"))
                .authorities("user")
                .roles("READ_ONLY")
                .build();
        return new InMemoryUserDetailsManager(user1,user2);
    }

    @Bean
    PasswordEncoder noOpEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


   /* //2.setting up Content Security Policy
        httpSecurity
                .headers(
    config ->
            config.contentTypeOptions(Customizer.withDefaults()) //default for enable()
            .xssProtection(Customizer.withDefaults()) //default for enable()
            .contentSecurityPolicy(
            csp -> csp.policyDirectives("default-src 'none'; frame-ancestors 'none'")
            )
            .frameOptions(fO -> fO.deny())
            .referrerPolicy( rP -> rP.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER))

            );
    //3.configuring HSTS
        httpSecurity
                .headers(config ->
            config.httpStrictTransportSecurity(
    hstsConfig ->
            hstsConfig
                    .includeSubDomains(true)
            .maxAgeInSeconds(31536000)
                                            .preload(false)
                                            .requestMatcher(AnyRequestMatcher.INSTANCE)
                        ));*/

}
