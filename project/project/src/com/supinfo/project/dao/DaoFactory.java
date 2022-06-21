package com.supinfo.project.dao;

import com.supinfo.project.dao.impl.JpaStatDao;
import com.supinfo.project.dao.impl.JpaUrlDao;
import com.supinfo.project.dao.impl.JpaUserDao;
import com.supinfo.project.util.PersistenceManager;


public class DaoFactory {

	
	
	public static UrlDao getUrlDao() {
		return new JpaUrlDao(PersistenceManager.getEntityManagerFactory());
	}
	
	
	public static UserDao getUserDao() {
		return new JpaUserDao(PersistenceManager.getEntityManagerFactory());
	}
	
	public static StatDao getStatDao() {
		return new JpaStatDao(PersistenceManager.getEntityManagerFactory());
	}
}
