package com.kruger.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {
	
	  private static final String[] WHITE_LIST_URLS = {
	            "/hello",
	            "/register",
	            "/verifyRegistration*",
	            "/resendVerifyToken*"
	    };

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	      var t= PasswordEncoderFactories.createDelegatingPasswordEncoder();
	      return t;
	    }

	    @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	                .cors()
	                .and()
	                .csrf()
	                .disable()
	                .authorizeHttpRequests()
	                .requestMatchers("/**").permitAll()
	                .requestMatchers("/**").authenticated()
	                .and()
	                .oauth2Login(oauth2login ->
	                        oauth2login.loginPage("/oauth2/authorization/api-client-oidc"))
	                .oauth2Client(Customizer.withDefaults());

	        return http.build();
	    }

}
