package mum.edu.cs544.volunteer.dao;

import java.util.List;

import com.sun.jmx.snmp.tasks.Task;

import mum.edu.cs544.volunteer.domain.Project;

public interface IProjectDAO {
	void createProject(Project project);
	void updateProject(Project project);
	List<Project> getAllProjects();
	List<Project> getAllCompletedProjects();
	List<Project> getAllIncompletedProjects();
	List<Project> searchProjectByResource(String resource);
	List<Project> searchProjectByKeyword(String keyword);
	List<Project> searchProjectByLocation(String location);
	List<Project> getAllProjectsHavingVolunteer();
}
