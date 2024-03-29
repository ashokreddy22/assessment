package com.in.hotel.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@SuppressWarnings({ "deprecation" })
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
private CustomerUsersDetailsService customerUsersDetailsService;
	@Autowired
	private JwtFilter jwtFilter;
	
@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(customerUsersDetailsService);
	}

@Bean
public PasswordEncoder passwordEncoder() {
	return NoOpPasswordEncoder.getInstance();
}
	
	
@Bean(name="BeanId.AUTHENTICATION_MANAGER")
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}


@SuppressWarnings("deprecation")
@Override
protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.cors().configurationSource(request->new CorsConfiguration().applyPermitDefaultValues())
 
	        .and()
	        .csrf().disable()
	        .authorizeRequests()
            .antMatchers("/user/login","/user/signUp","/user/forgotPassword","/user/get")
            .permitAll()
//            .antMatchers(HttpHeaders.ALLOW).permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .exceptionHandling()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    
    httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
}


	}

	
	

