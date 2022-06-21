package com.supinfo.project.dao;

import java.util.List;

import com.supinfo.project.entity.Url;


public interface UrlDao {
	
	
	Url addUrl(Url p);
	
	
	Url findUrlById(Long id);
	void enabledisableById(Long id, Boolean enable);
	
	
	List<Url> getAllUrls();
	
	
	void removeUrl(Long id);

	
	List<Url> getUrlsByUser(Long idUser);
	Url findUrlByLink(String link);
	
}
