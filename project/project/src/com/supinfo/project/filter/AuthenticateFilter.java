package com.supinfo.project.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebFilter(urlPatterns="/auth/*")
public class AuthenticateFilter implements Filter {

	
	private static final String USERNAME_PARAMETER_NAME = "username";
	
	private static final String LOGIN_VIEW 				= "/login.jsp";
    
	public void init(FilterConfig config) throws ServletException {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		final HttpSession session = httpRequest.getSession();
		final Object usernameParameter = session.getAttribute(USERNAME_PARAMETER_NAME);
		
		
		if(usernameParameter != null) {
			chain.doFilter(request, response);
		} else {
			
			httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_VIEW);
		}
	}

	public void destroy() {
		
	}

}