package com.vince.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AttachForPageEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Date createDate;
	@OneToOne
	private BlobEntity pageHtml;
	@OneToOne
	private BlobEntity pageHtmlDelta;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BlobEntity getPageHtml() {
		return pageHtml;
	}
	public void setPageHtml(BlobEntity pageHtml) {
		this.pageHtml = pageHtml;
	}
	public BlobEntity getPageHtmlDelta() {
		return pageHtmlDelta;
	}
	public void setPageHtmlDelta(BlobEntity pageHtmlDelta) {
		this.pageHtmlDelta = pageHtmlDelta;
	}

}
