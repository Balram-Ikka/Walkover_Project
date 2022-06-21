package com.supinfo.project.dao;

import java.util.List;

import com.supinfo.project.entity.Stat;


public interface StatDao {
	
	
	Stat addStat(Stat p);
	
	
	Stat findStatById(Long id);
	
	
	
	
	void removeStat(Long id);

	List<Stat> findStatByUrlId(Long idUrl);
		
}
