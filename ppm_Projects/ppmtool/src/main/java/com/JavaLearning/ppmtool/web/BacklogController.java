package com.JavaLearning.ppmtool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
											BindingResult result, @PathVariable String backlog_id){
		
		ResponseEntity<?> errormap = mapValidationErrorService.MapValidationService(result);
    	if(errormap!=null) {
    		return errormap;
    	}
    	ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);
    	
    	return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
	}
	

}
