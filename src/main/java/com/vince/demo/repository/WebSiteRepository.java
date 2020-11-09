package com.vince.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vince.demo.entity.WebSite;

public interface WebSiteRepository extends CrudRepository<WebSite, Long> {

	List<WebSite> findByName(String name);
	
	List<WebSite> findByIsActive(boolean  isActive);
	
	WebSite findByUrl(String url);

	WebSite findById(long id);
}
