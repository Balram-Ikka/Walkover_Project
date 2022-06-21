package com.supinfo.project.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.project.dao.DaoFactory;
import com.supinfo.project.dao.UrlDao;
import com.supinfo.project.entity.Url;


public class EnableUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final String ID_PARAMETER_NAME = "id";
    private static final String ENABLE_PARAMETER_NAME = "enable"; 
	
    private static final String SHOW_Url_VIEW = "/auth/listUrl";
	
    private UrlDao UrlDao;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		UrlDao = DaoFactory.getUrlDao();
	}
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String idParameter = request.getParameter(ID_PARAMETER_NAME);
		final String enableParameter = request.getParameter(ENABLE_PARAMETER_NAME);
		Url requestedUrl = new Url();
		
		if(idParameter != null) {
			try {
				final Long idLong = Long.parseLong(idParameter);
				requestedUrl = UrlDao.findUrlById(idLong);
				System.out.println(enableParameter);
				if (enableParameter.equals("1"))	{
					System.out.println("ok");
					UrlDao.enabledisableById(idLong, true);
				}
				else	{
					UrlDao.enabledisableById(idLong, false);
				}
			} catch(NumberFormatException nfe) {
				System.out.println("ERROR: ");
			}
		}
		
		request.setAttribute("Url", requestedUrl);
		
		request.getRequestDispatcher(SHOW_Url_VIEW).forward(request, response);
	}
}
