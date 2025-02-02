package com.jmv.jdbc.rca;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/index.jsp", "/guest.jsp" })
public class AuthFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		// Initialization code, if needed.
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);

		if (session != null && session.getAttribute("user") != null) {
			chain.doFilter(request, response);
		} else {
			session.invalidate();
			httpResponse.sendRedirect(httpRequest.getContextPath() + ("login"));
		}
	}

	public void destroy() {
	}
}
