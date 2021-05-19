package com.JavaLearning.ppmtool.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Backlog {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		/* the sequence of project tasks within each backlog.*/
		private Integer PTSequence=0;
		private String projectIdentifier;
		
		
		// OneToOne with Project
/*each project has one backlog and a backlog only belongs to that project to up to our specific project.	*/
	   
	    @OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name="project_id",nullable = false)
	    
	    @JsonIgnore
	    /*Could not write JSON: Infinite recursion (StackOverflowError);
	     * getting above error on removing above @JsonIgnore tag
	     * */
	    private Project project;
	    
	    
		//OneToMany ProjectTask
	    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "backlog", orphanRemoval = true)
	    private List<ProjectTask> projectTaskList = new ArrayList<>();
	    
	/* Each backlog can have more than one task but each projectTask belong to only one project backlog */
		public Backlog() {
			
			
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Integer getPTSequence() {
			return PTSequence;
		}


		public void setPTSequence(Integer pTSequence) {
			PTSequence = pTSequence;
		}


		public String getProjectIdentifier() {
			return projectIdentifier;
		}


		public void setProjectIdentifier(String projectIdentifier) {
			this.projectIdentifier = projectIdentifier;
		}


		public Project getProject() {
			return project;
		}


		public void setProject(Project project) {
			this.project = project;
		}


		public List<ProjectTask> getProjectTaskList() {
			return projectTaskList;
		}


		public void setProjectTaskList(List<ProjectTask> projectTaskList) {
			this.projectTaskList = projectTaskList;
		}

		
		
		
}
