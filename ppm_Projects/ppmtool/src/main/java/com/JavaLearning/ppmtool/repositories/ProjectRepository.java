package com.JavaLearning.ppmtool.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.JavaLearning.ppmtool.domain.Project;



@Repository
public interface ProjectRepository extends CrudRepository<Project,Long>{
	
	Project findByProjectIdentifier(String projectIdentifier);

	
	@Override
	Iterable<Project> findAll();
	
	Iterable<Project> findAllByProjectLeader(String projectLeader);
	
}


