package com.supinfo.project.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.UserDao;
import com.supinfo.project.entity.User;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;
	private static final String USERNAME_PARAMETER_NAME = "username";
	private static final String PASSWORD_PARAMETER_NAME = "password";
	
	private static final String ID_USER = "";
	private static final String LIST_PRODUCT_SERVLET	= "/auth/listUrl";
	private static final String LOGIN_VIEW 				= "/login.jsp";

	public void init() throws ServletException {
		super.init();

		userDao = DaoFactory.getUserDao();
	}


	private static String encode(String password, String algorithm)	throws NoSuchAlgorithmException {
		byte[] hash = null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			hash = md.digest(password.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hash.length; ++i) {
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				sb.append(0);
				sb.append(hex.charAt(hex.length() - 1));
			} else {
				sb.append(hex.substring(hex.length() - 2));
			}
		}
		return sb.toString();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String usernameParameter = request.getParameter(USERNAME_PARAMETER_NAME);
		final String passwordParameter = request.getParameter(PASSWORD_PARAMETER_NAME);
			
		
		
		try {
			
			final User user = userDao.findUserByNamePassword(usernameParameter,encode(passwordParameter, "MD5"));
			if (user != null)	{
				final HttpSession session = request.getSession();
				session.setAttribute(USERNAME_PARAMETER_NAME, usernameParameter);
				session.setAttribute(ID_USER, user.getId());
			
				response.sendRedirect(request.getServletContext().getContextPath()+LIST_PRODUCT_SERVLET);
			}
			else	{
				response.sendRedirect(request.getServletContext().getContextPath()+LOGIN_VIEW);
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getServletContext().getContextPath()+LOGIN_VIEW);

	}

}

