package mum.edu.cs544.volunteer.control;

import mum.edu.cs544.volunteer.dao.IUserDAO;
import mum.edu.cs544.volunteer.dao.UserDAO;
import mum.edu.cs544.volunteer.domain.User;

public class Application {
	
	public static void main(String[] args) {
		System.out.println("Hello");
		IUserDAO dao = new UserDAO();
		User u = new User();
		u.setName("Dilip");
		dao.create(u);
	}

}
