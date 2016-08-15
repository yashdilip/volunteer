package mum.edu.cs544.volunteer.service;

import mum.edu.cs544.volunteer.dao.IProjectDAO;
import mum.edu.cs544.volunteer.dao.ITaskDAO;
import mum.edu.cs544.volunteer.dao.IUserDAO;
import mum.edu.cs544.volunteer.dao.ProjectDAO;
import mum.edu.cs544.volunteer.dao.TaskDAO;
import mum.edu.cs544.volunteer.dao.UserDAO;
import mum.edu.cs544.volunteer.domain.Address;
import mum.edu.cs544.volunteer.domain.Project;
import mum.edu.cs544.volunteer.domain.Task;
import mum.edu.cs544.volunteer.domain.User;

public class ServiceImpl implements IService {
	IUserDAO userDAO = new UserDAO();
	IProjectDAO projectDAO = new ProjectDAO();	
	ITaskDAO taskDAO = new TaskDAO();

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
	
}
