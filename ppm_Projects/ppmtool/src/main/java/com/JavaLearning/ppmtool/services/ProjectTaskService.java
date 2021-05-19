package com.JavaLearning.ppmtool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaLearning.ppmtool.domain.Backlog;
import com.JavaLearning.ppmtool.domain.ProjectTask;
import com.JavaLearning.ppmtool.repositories.BacklogRepository;
import com.JavaLearning.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private BacklogRepository backlogRepository ;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		
		//Project : Exception not found
		//project task added to specific project, project!=null, backlog exist
	
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
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
		
	}


	public List<ProjectTask> findBacklogById(String backlog_id) {
		// TODO Auto-generated method stub
		Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id.toUpperCase());
		
		return projectTaskRepository.findByprojectIdentifier(backlog_id);
	}
}
