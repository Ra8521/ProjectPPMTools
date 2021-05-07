package com.JavaLearning.ppmtool.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String projectName;
	private String projectIdentifier;
	private String description;
	private Date start_date;
	private Date end_date;
	
	private Date created_At;
	private Date updated_At;
	
	private Project() {
		
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getProjectIdentifier() {
		return projectIdentifier;
	}


	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getStart_date() {
		return start_date;
	}


	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}


	public Date getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}


	public Date getCreated_At() {
		return created_At;
	}


	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}


	public Date getUpdated_At() {
		return updated_At;
	}


	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}


	/* to track date of creation and updation of project*/
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
		 
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}
	 
	
	 
}
