package com.JavaLearning.ppmtool.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavaLearning.ppmtool.domain.ProjectTask;
import com.JavaLearning.ppmtool.services.MapValidationErrorService;
import com.JavaLearning.ppmtool.services.ProjectTaskService;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	@PostMapping("/{backlog_id}")
	public ResponseEntity<?> addPTtoBacklog(@Validated @RequestBody ProjectTask projectTask,
											BindingResult result, @PathVariable String backlog_id, Principal principal){
		
		ResponseEntity<?> errormap = mapValidationErrorService.MapValidationService(result);
    	if(errormap!=null) {
    		return errormap;
    	}
    	ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask, principal.getName());
    	
    	return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
	}
	
	@GetMapping("/{backlog_id}")
	public ResponseEntity<List<ProjectTask>> getPTtoBacklog(@PathVariable String backlog_id, Principal principal){
		
		 return new ResponseEntity<List<ProjectTask>>(projectTaskService.findBacklogById(backlog_id,principal.getName()), 
				 											HttpStatus.OK);
		
	}
	
	@GetMapping("/{backlog_id}/{projectTaskSeq}")
	public ResponseEntity<ProjectTask> getProjectTask(@PathVariable String backlog_id, @PathVariable String projectTaskSeq, Principal principal){
		ProjectTask projectTask = projectTaskService.findProjectTaskBySequence(backlog_id,projectTaskSeq,principal.getName());
		return new  ResponseEntity<ProjectTask> (projectTask,HttpStatus.OK);
	}
	
	
			 @PatchMapping("/{backlog_id}/{projectTaskSeq}")
	public ResponseEntity<?> updateProjectTask(@Validated @RequestBody ProjectTask projectTask,BindingResult result,
			@PathVariable String backlog_id, @PathVariable String projectTaskSeq,Principal principal){
		ResponseEntity<?> errormap = mapValidationErrorService.MapValidationService(result);
    	if(errormap!=null) {
    		return errormap;
    	}
    	ProjectTask updateTask = projectTaskService.updateByProjectTask(projectTask, backlog_id, projectTaskSeq,principal.getName() );
    	return new ResponseEntity<ProjectTask>(updateTask,HttpStatus.OK);
	}
	@DeleteMapping("/{backlog_id}/{projectTaskSeq}")
	public ResponseEntity<?> deleteProjectTask(
			@PathVariable String backlog_id, @PathVariable String projectTaskSeq, Principal principal){
		
    	projectTaskService.deleteByProjectTask(backlog_id, projectTaskSeq, principal.getName());
    	return new ResponseEntity<String>("Project Task "+projectTaskSeq+" was deleted successfully", HttpStatus.OK);
	}
}
