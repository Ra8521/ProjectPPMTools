package com.JavaLearning.ppmtool.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.JavaLearning.ppmtool.domain.Project;
import com.JavaLearning.ppmtool.domain.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long>{
	List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
	
	ProjectTask findByProjectSequence(String projectSequence);
}
