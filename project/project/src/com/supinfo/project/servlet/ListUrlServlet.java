package com.supinfo.project.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.UrlDao;
import com.supinfo.project.entity.Url;


public class ListUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String LIST_Url_PARAMETER = "urls";

	private static final String LIST_Url_VIEW = "listUrl.jsp";

	private static final String ID_USER = "";

	private UrlDao UrlDao;

	
	@Override
	public void init() throws ServletException {
		super.init();
		UrlDao = DaoFactory.getUrlDao();
	}


	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpSession session = httpRequest.getSession();
		final Object idUserParameter = session.getAttribute(ID_USER);
		final Long idUser = (Long) idUserParameter;


		final List<Url> allUrls = UrlDao.getUrlsByUser(idUser);

		

		request.setAttribute(LIST_Url_PARAMETER, allUrls);
		request.setAttribute("connected", true);
		request.getRequestDispatcher(LIST_Url_VIEW)
		.forward(request, response);
	}
}
