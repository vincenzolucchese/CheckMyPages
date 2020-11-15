package com.vince.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vince.demo.entity.WebSiteEntity;

public interface WebSiteRepository extends CrudRepository<WebSiteEntity, Long> {

	List<WebSiteEntity> findByName(String name);
	
	List<WebSiteEntity> findByIsActive(boolean  isActive);
	
	WebSiteEntity findByUrl(String url);

	WebSiteEntity findById(long id);
}
