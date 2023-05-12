package com.in.hotel.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class JwtFilter extends OncePerRequestFilter {

	String token = null;
	Claims claims = null;
	private String username = null;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomerUsersDetailsService customerUsersDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		if (httpServletRequest.getServletPath().matches("/user/login|/user/forgotPassword|/user/signUp|/user/get")) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} else {

			String requestTokenHeader = httpServletRequest.getHeader("Authorization");
//			String token = null;

			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				token = requestTokenHeader.substring(7);

				username = jwtUtil.ExtractUserName(token);
				claims = jwtUtil.extractAllClaims(token);

			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = customerUsersDetailsService.loadUserByUsername(username);

				if (jwtUtil.validateToken(token, userDetails)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(httpServletRequest, httpServletResponse);

		}
	}

	public boolean isAdmin() {

		boolean c = "admin".equalsIgnoreCase((String) customerUsersDetailsService.getUserDetail().getRole());

		return c;
	}

	public boolean isUser() {

		boolean c = "user".equalsIgnoreCase((String) customerUsersDetailsService.getUserDetail().getRole());

		return c;
	}

	public String getCurrentUser() {
		return username;
	}

}