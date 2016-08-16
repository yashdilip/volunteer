package mum.edu.cs544.volunteer.service;

import java.util.List;

import mum.edu.cs544.volunteer.domain.Address;
import mum.edu.cs544.volunteer.domain.Beneficiary;
import mum.edu.cs544.volunteer.domain.Project;
import mum.edu.cs544.volunteer.domain.Task;
import mum.edu.cs544.volunteer.domain.User;

public interface IService {
	void createUser(User user);
	void saveUserAddress(Address address);
	void createNewProject(Project project);
	void saveProjectTask(Task task);
	void createBeneficiary(Beneficiary beneficiary);
	Project getProjectByName(String name);
	void updateProject(Project project);
	List<Task> getTasksByResource(String str);
	void updateTask(Task t);
	List<User> getAllUsers();
	List<Project> getAllProjects();
}
