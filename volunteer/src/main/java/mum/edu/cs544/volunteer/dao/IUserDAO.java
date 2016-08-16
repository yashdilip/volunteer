package mum.edu.cs544.volunteer.dao;

import java.util.List;

import mum.edu.cs544.volunteer.domain.Address;
import mum.edu.cs544.volunteer.domain.User;

public interface IUserDAO {
	void create(User user);
	void saveNewAddress(Address address);
	boolean authenticateUser(User user);
	List<User> getAllUsers();
	
}
