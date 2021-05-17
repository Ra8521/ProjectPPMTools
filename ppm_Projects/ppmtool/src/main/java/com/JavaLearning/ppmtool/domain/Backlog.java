package com.JavaLearning.ppmtool.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Backlog {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		private Integer PTSequence=0;
		private String ProjectIdentifier;
		
		
		// OneToOne with Project
		
		//OneToMant ProjectTask
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
			return ProjectIdentifier;
		}


		public void setProjectIdentifier(String projectIdentifier) {
			ProjectIdentifier = projectIdentifier;
		}
		
		
		
		
}
