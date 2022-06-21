package com.supinfo.project.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.UrlDao;
import com.supinfo.project.dao.UserDao;
import com.supinfo.project.entity.Url;
import com.supinfo.project.entity.User;


public class AddUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ID_USER = "";
	private static final String NAME_PARAMETER 			= "name";
	private static final String URL_PARAMETER 		= "url";

	
	private UrlDao UrlDao;
	private UserDao userDao;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		UrlDao = DaoFactory.getUrlDao();
		userDao = DaoFactory.getUserDao();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String name = request.getParameter(NAME_PARAMETER);
		final String url = request.getParameter(URL_PARAMETER);
		
		int lower = 1;
		int higher = 10000;
		int random = (int)(Math.random() * (higher-lower)) + lower;
		
		final String urlGenere = String.valueOf(random) ;

		final Url Url = new Url();
		Url.setName(name);
		Url.setUrl(url);
		Url.setUrlGenere(urlGenere);
		Url.setEnable(true);

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpSession session = httpRequest.getSession();
		final Object idUserParameter = session.getAttribute(ID_USER);
		final Long idUser = (Long) idUserParameter;
		final User user = userDao.findUserById(idUser);
		Url.setUser(user);
		
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
		Url.setDate(dateFormat.format(date));

		UrlDao.addUrl(Url);

		
		response.sendRedirect(request.getServletContext().getContextPath()+	"/auth/listUrl");
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 
	}
}
