package com.JavaLearning.ppmtool.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.JavaLearning.ppmtool.domain.User;
import com.JavaLearning.ppmtool.services.CustomUserDetailsService;
import static com.JavaLearning.ppmtool.security.SecurityConstant.*;
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String jwt = getJWTFomRequest(request);
			if(StringUtils.hasText(jwt)&&jwtTokenProvider.validateToken(jwt)) {
				Long userId = jwtTokenProvider.getUserIdFromJwt(jwt);
				User userDetail = customUserDetailsService.loadUserById(userId);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetail,null,Collections.emptyList()
						);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
		}catch(Exception ex) {
			logger.error("Could not set user authentication in security context ",ex);
			
		}
		filterChain.doFilter(request, response);
		
	}
	
	
	private String getJWTFomRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(HEADER_STRING);
		
		if(StringUtils.hasText(bearerToken)&& bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(7,bearerToken.length());
		}
		return null;
		
	}

}
