package com.JavaLearning.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.JavaLearning.ppmtool.domain.Backlog;
import com.JavaLearning.ppmtool.domain.Project;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long>{
	
	 Backlog findByProjectIdentifier(String Identifier);

}
