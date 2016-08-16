package mum.edu.cs544.volunteer.service;

import java.util.List;

import mum.edu.cs544.volunteer.dao.BeneficiaryDAO;
import mum.edu.cs544.volunteer.dao.IBeneficiaryDAO;
import mum.edu.cs544.volunteer.dao.IProjectDAO;
import mum.edu.cs544.volunteer.dao.ITaskDAO;
import mum.edu.cs544.volunteer.dao.IUserDAO;
import mum.edu.cs544.volunteer.dao.ProjectDAO;
import mum.edu.cs544.volunteer.dao.TaskDAO;
import mum.edu.cs544.volunteer.dao.UserDAO;
import mum.edu.cs544.volunteer.domain.Address;
import mum.edu.cs544.volunteer.domain.Beneficiary;
import mum.edu.cs544.volunteer.domain.Project;
import mum.edu.cs544.volunteer.domain.Task;
import mum.edu.cs544.volunteer.domain.User;

public class ServiceImpl implements IService {
	IUserDAO userDAO = new UserDAO();
	IProjectDAO projectDAO = new ProjectDAO();	
	ITaskDAO taskDAO = new TaskDAO();
	IBeneficiaryDAO beneficiaryDAO = new BeneficiaryDAO();

	public void createUser(User user) {
		userDAO.create(user);
	}

	public void createNewProject(Project project) {
		projectDAO.createProject(project);
	}

	public void saveUserAddress(Address address) {
		userDAO.saveNewAddress(address);
	}

	public void saveProjectTask(Task task) {
		taskDAO.createTask(task);
	}

	public void createBeneficiary(Beneficiary beneficiary) {
		beneficiaryDAO.createBeneficiary(beneficiary);
	}

	public Project getProjectByName(String name) {
		return projectDAO.getProjectById(name);
	}

	public void updateProject(Project project) {
		projectDAO.updateProjectAssignedToUser(project);
		
	}

	public List<Task> getTasksByResource(String str) {
		return taskDAO.getTaskBySkill(str);
	}

	public void updateTask(Task t) {
		taskDAO.updateTask(t);
	}

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	public List<Project> getAllProjects() {
		return projectDAO.getAllProjects();
	}

	public List<Task> getAllTasksByProjectId(int projectId) {
		return taskDAO.getAllTaskByProjectId(projectId);
	}

	public List<Project> getAllCompletedProjects() {
		return projectDAO.getAllCompletedProjects();
	}

	public List<Project> getAllIncompletedProjects() {
		return projectDAO.getAllIncompletedProjects();
	}

	public List<Project> getProjectsByResource(String resources) {
		return projectDAO.searchProjectByResource(resources);
	}

	public List<Project> getProjectsByKeyword(String keyword) {
		return projectDAO.searchProjectByKeyword(keyword);
	}

	public List<Project> getProjectsByLocation(String location) {
		return projectDAO.searchProjectByLocation(location);
	}

	public List<Project> getAllProjectsAndTaskHavingVolunteer() {
		return projectDAO.getAllProjectsHavingVolunteer();
	}

	public List<Beneficiary> getAllBeneficiariesOfProjectById(int projectId) {
		return beneficiaryDAO.getAllBeneficiariesByProjectId(projectId);
	}	
}
