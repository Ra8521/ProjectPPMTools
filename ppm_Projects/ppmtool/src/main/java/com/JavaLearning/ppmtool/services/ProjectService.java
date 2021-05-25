package com.JavaLearning.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaLearning.ppmtool.domain.Backlog;
import com.JavaLearning.ppmtool.domain.Project;
import com.JavaLearning.ppmtool.domain.User;
import com.JavaLearning.ppmtool.exceptions.ProjectIdException;
import com.JavaLearning.ppmtool.exceptions.ProjectNotFoundException;
import com.JavaLearning.ppmtool.repositories.BacklogRepository;
import com.JavaLearning.ppmtool.repositories.ProjectRepository;
import com.JavaLearning.ppmtool.repositories.UserRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    public Project saveOrUpdateProject(Project project, String username){
    	
    	String setIdentifier = project.getProjectIdentifier().toUpperCase();
    	if(project.getId() != null){
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if(existingProject !=null &&(!existingProject.getProjectLeader().equals(username))){
                throw new ProjectNotFoundException("Project not found in your account");
            }else if(existingProject == null){
                throw new ProjectNotFoundException("Project with ID: '"+project.getProjectIdentifier()+"' cannot be updated because it doesn't exist");
            }
        }

    	
    	
        try{
        	User user = userRepository.findByUsername(username);
        	project.setUser(user);
        	project.setProjectLeader(user.getUsername());
            project.setProjectIdentifier(setIdentifier);

            /* create new on*/
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
	
	public Project findProjectByIdentifier(String ProjectId, String username) {
		Project project =   projectRepository.findByProjectIdentifier(ProjectId.toUpperCase());
		
		if(project==null) {
			throw new ProjectIdException("Project id: "+ProjectId+" does not exist");
		}
		if(!project.getProjectLeader().equals(username)) {
			throw new ProjectNotFoundException("Project not found under your account");
		}
		return project;
	}
	
	public Iterable<Project> findAllProject(String username){
		return projectRepository.findAllByProjectLeader(username);
	
		
	}
	
	public void deleteProjectByIdentifier(String projectId, String username) {
		projectRepository.delete(findProjectByIdentifier(projectId,username));
	}
	
	
}
