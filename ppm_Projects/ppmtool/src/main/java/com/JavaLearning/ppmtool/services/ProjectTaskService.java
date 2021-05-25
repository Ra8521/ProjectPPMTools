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
	
	@Autowired
	private ProjectService projectService;
	
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {
		
		projectIdentifier = projectIdentifier.toUpperCase();
		//Project : Exception not found
		/*
		 * project not found
		 */
	
		//project task added to specific project, project!=null, backlog exist
	Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();
	
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
		projectTask.setProjectIdentifier(projectIdentifier);
		
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


	public List<ProjectTask> findBacklogById(String ProjectIdentifier, String username) {
		// TODO Auto-generated method stub
		
		projectService.findProjectByIdentifier(ProjectIdentifier, username);
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(ProjectIdentifier);
	}
	
	public ProjectTask findProjectTaskBySequence(String ProjectIdentifier, String projectSequence, String username) {
		
		//make sure searching backlog is exist
		
		projectService.findProjectByIdentifier(ProjectIdentifier, username);
		
		//make sure project task exist in repository
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);
		if(projectTask==null) {
			throw new ProjectNotFoundException("Project task does not exist with id: "+projectSequence);
		}
		
		//make sure project task are part of right project backlog
		if(!(projectTask.getProjectIdentifier().equals(ProjectIdentifier))) {
			throw new ProjectNotFoundException("Project task with id: "+
					projectSequence+" does not exist in project "+ProjectIdentifier);
		}
		return projectTask;
	}
	
	public ProjectTask updateByProjectTask(ProjectTask updatedTask, String backlog_id, String pt_id, String username) {
		
		//validating update request
		findProjectTaskBySequence(backlog_id,pt_id, username);
		
		return projectTaskRepository.save(updatedTask);
		
	}




	public void deleteByProjectTask(String backlog_id, String projectTaskSeq,String username) {
		// TODO Auto-generated method stub
		ProjectTask projectask = findProjectTaskBySequence(backlog_id,projectTaskSeq,username);
		/*
		Backlog backlog = projectask.getBacklog();
		List<ProjectTask> projectTaskList = backlog.getProjectTaskList();
		projectTaskList.remove(projectask);
		backlogRepository.save(backlog);
		*/
		projectTaskRepository.delete(projectask);
	}
}
