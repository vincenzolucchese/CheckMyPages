package com.vince.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class WebSiteEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String name;
	@Column(unique = true)
	private String url;
	private boolean isActive;
	private Date lastCheck;
	
    @OneToMany
    private List<AttachForPageEntity> attachment = new ArrayList<>();  
	
	protected WebSiteEntity() {}

	public WebSiteEntity(String firstName, String lastName, boolean isActive) {
		this.name = firstName;
		this.url = lastName;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getLastCheck() {
		return lastCheck;
	}

	public void setLastCheck(Date lastCheck) {
		this.lastCheck = lastCheck;
	}

	@Override
	public String toString() {
		return "WebSite [id=" + id + ", name=" + name + ", url=" + url + ", isActive=" + isActive + ", lastCheck="
				+ lastCheck + "]";
	}

	public List<AttachForPageEntity> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<AttachForPageEntity> attachment) {
		this.attachment = attachment;
	}


}
