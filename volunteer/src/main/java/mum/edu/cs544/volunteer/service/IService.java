package mum.edu.cs544.volunteer.service;

import mum.edu.cs544.volunteer.domain.Address;
import mum.edu.cs544.volunteer.domain.Project;
import mum.edu.cs544.volunteer.domain.Task;
import mum.edu.cs544.volunteer.domain.User;

public interface IService {
	void createUser(User user);
	void saveUserAddress(Address address);
	void createNewProject(Project project);
	void saveProjectTask(Task task);
}
