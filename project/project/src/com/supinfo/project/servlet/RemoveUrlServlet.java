package com.supinfo.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.StatDao;
import com.supinfo.project.dao.UrlDao;


public class RemoveUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final String ID_PARAMETER_NAME = "id";   

	private static final String LIST_Url_VIEW = "/auth/listUrl";
       
	private UrlDao UrlDao;
	private StatDao StatDao;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		UrlDao = DaoFactory.getUrlDao();
		StatDao = DaoFactory.getStatDao();
	}
	
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String idParameter = request.getParameter(ID_PARAMETER_NAME);
		
		if(idParameter != null) {
			try {
				final Long idLong = Long.parseLong(idParameter);
				StatDao.removeStat(idLong);
				UrlDao.removeUrl(idLong);
			} catch(NumberFormatException nfe) {
				System.out.println("ERROR: l'id entré est n'est pas un nombre.");
			}
		}
		
		response.sendRedirect(request.getServletContext().getContextPath()+LIST_Url_VIEW);
	}
}
