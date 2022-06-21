package com.supinfo.project.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.supinfo.project.util.PersistenceManager;


public class PersistenceListener implements ServletContextListener {

	
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

	
    public void contextDestroyed(ServletContextEvent arg0) {
        PersistenceManager.closeEntityManagerFactory();
    }
	
}
