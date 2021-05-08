package com.JavaLearning.ppmtool.exceptions;

public class ProjectIdExceptionResponse {
	private String projectIdentifier;
	
	

	public   ProjectIdExceptionResponse(String exception) {
		this.projectIdentifier = exception;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	
}
