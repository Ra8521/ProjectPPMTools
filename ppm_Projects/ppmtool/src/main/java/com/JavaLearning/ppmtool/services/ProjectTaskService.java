package com.JavaLearning.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaLearning.ppmtool.domain.Backlog;
import com.JavaLearning.ppmtool.domain.ProjectTask;
import com.JavaLearning.ppmtool.exceptions.ProjectIdException;
import com.JavaLearning.ppmtool.exceptions.ProjectNotFoundException;
import com.JavaLearning.ppmtool.repositories.BacklogRepository;
import com.JavaLearning.ppmtool.repositories.ProjectRepository;
import com.JavaLearning.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private BacklogRepository backlogRepository ;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		projectIdentifier = projectIdentifier.toUpperCase();
		//Project : Exception not found
		/*
		 * project not found
		 */
	
		//project task added to specific project, project!=null, backlog exist
	Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
	
	try {
	//set backlog to project task
		projectTask.setBacklog(backlog);
		//we will keep project sequence like: IDPRO-1, IDPRO-2 etc;
		Integer BacklogSequence = backlog.getPTSequence();
		//update backlog sequence
		BacklogSequence++;
		backlog.setPTSequence(BacklogSequence);
		//Add sequence to project task
		projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
		projectTask.setProjectIdentifer(projectIdentifier);
		
		// intial prioprity when priority is null  3-low, 1-high
		if(projectTask.getPriority()==null || projectTask.getPriority()==0 ) {
			projectTask.setPriority(3);
		}
		
		//Intial status when status is null
		if(projectTask.getStatus()==""||projectTask.getStatus()==null) {
			projectTask.setStatus("TO_DO");
		}
		
		return projectTaskRepository.save(projectTask);
	} catch(Exception e) {
		throw new ProjectNotFoundException("Project not found");
	}
	}


	public List<ProjectTask> findBacklogById(String ProjectIdentifier) {
		// TODO Auto-generated method stub
		
		ProjectIdentifier = ProjectIdentifier.toUpperCase();
		if(projectRepository.findByProjectIdentifier(ProjectIdentifier)==null) {
			throw new ProjectNotFoundException("Project does not exist with id: "+ProjectIdentifier);
		}
		
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(ProjectIdentifier);
	}
	
	public ProjectTask findProjectTaskBySequence(String ProjectIdentifier, String projectSequence) {
		
		//make sure searching backlog is exist
		
		Backlog backlog = backlogRepository.findByProjectIdentifier(ProjectIdentifier);
		if(backlog==null) {
			throw new ProjectNotFoundException("Project does not exist with id: "+ProjectIdentifier);
		}
		//make sure project task exist in repository
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);
		if(projectTask==null) {
			throw new ProjectNotFoundException("Project task does not exist with id: "+projectSequence);
		}
		
		//make sure project task are part of right project backlog
		if(!(projectTask.getProjectIdentifer().equals(ProjectIdentifier))) {
			throw new ProjectNotFoundException("Project task with id: "+
					projectSequence+" does not exist in project "+ProjectIdentifier);
		}
		return projectTask;
	}
}
