package com.supinfo.project.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.StatDao;
import com.supinfo.project.dao.UrlDao;
import com.supinfo.project.entity.Stat;
import com.supinfo.project.entity.Url;


public class ShowUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ID_PARAMETER_NAME = "id";   

	private static final String SHOW_Url_VIEW = "/auth/showUrl.jsp";

	private UrlDao UrlDao;
	private StatDao StatDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		UrlDao = DaoFactory.getUrlDao();
		StatDao = DaoFactory.getStatDao();

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String idParameter = request.getParameter(ID_PARAMETER_NAME);
		Url requestedUrl = new Url();

		if(idParameter != null) {
			try {
				final Long idLong = Long.parseLong(idParameter);
				requestedUrl = UrlDao.findUrlById(idLong);
				List<Stat> requestedStat = StatDao.findStatByUrlId(idLong);

				request.setAttribute("url", requestedUrl);
				request.setAttribute("stat", requestedStat);
			} catch(NumberFormatException nfe) {
				System.out.println("ERROR: l'id entré est n'est pas un nombre.");
			}
		}

		request.setAttribute("connected", true);
		request.getRequestDispatcher(SHOW_Url_VIEW).forward(request, response);
	}
}
