package com.JavaLearning.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaLearning.ppmtool.domain.Backlog;
import com.JavaLearning.ppmtool.domain.Project;
import com.JavaLearning.ppmtool.exceptions.ProjectIdException;
import com.JavaLearning.ppmtool.repositories.BacklogRepository;
import com.JavaLearning.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
    public Project saveOrUpdateProject(Project project){
    	String setIdentifier = project.getProjectIdentifier().toUpperCase();
        try{
        	
            project.setProjectIdentifier(setIdentifier);

            if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(setIdentifier);
            }

            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(setIdentifier));
            }

            return projectRepository.save(project);

        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+setIdentifier+"' already exists");
        }

    }
	
	public Project findProjectByIdentifier(String ProjectId) {
		Project project =   projectRepository.findByProjectIdentifier(ProjectId.toUpperCase());
		
		if(project==null) {
			throw new ProjectIdException("Project id: "+ProjectId+" does not exist");
		}
		return project;
	}
	
	public Iterable<Project> findAllProject(){
		return projectRepository.findAll();
	
		
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null) {
			throw new ProjectIdException("Peoject with Id: "+projectId+" does not exist");
		}
		projectRepository.delete(project);
	}
	
	
}
