package com.JavaLearning.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaLearning.ppmtool.domain.Project;
import com.JavaLearning.ppmtool.exceptions.ProjectIdException;
import com.JavaLearning.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		//logic
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}
		catch(Exception ex) {
			throw new ProjectIdException("Project id: "+project.getProjectIdentifier().toUpperCase()+" already exist");
		}
	}
	
	public Project findProjectByIdentifier(String ProjectId) {
		Project project =   projectRepository.findByProjectIdentifier(ProjectId.toUpperCase());
		
		if(project==null) {
			throw new ProjectIdException("Project id: "+ProjectId+" does not exist");
		}
		return project;
	}
}
