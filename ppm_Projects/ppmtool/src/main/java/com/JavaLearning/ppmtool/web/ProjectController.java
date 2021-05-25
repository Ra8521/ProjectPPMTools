package com.JavaLearning.ppmtool.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavaLearning.ppmtool.domain.Project;
import com.JavaLearning.ppmtool.services.MapValidationErrorService;
import com.JavaLearning.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private MapValidationErrorService MapValidationService;


    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Validated @RequestBody Project project, BindingResult result, Principal principal){

    	ResponseEntity<?> errormap = MapValidationService.MapValidationService(result);
    	if(errormap!=null) {
    		return errormap;
    	}
        Project project1 = projectService.saveOrUpdateProject(project,principal.getName());
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable("projectId") String projectId, Principal principal){
    	return new ResponseEntity<Project>(projectService.findProjectByIdentifier(projectId, principal.getName()), HttpStatus.OK);
    }
    @GetMapping("/all")
    public Iterable<Project> getAllProject(Principal principal){
    	return projectService.findAllProject(principal.getName());
    }
    
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable String projectId, Principal principal){
    	projectService.deleteProjectByIdentifier(projectId.toUpperCase(), principal.getName());
    	return new ResponseEntity<String>("Project with id: "+projectId + " is deleted", HttpStatus.OK);
    }
    
   
}
