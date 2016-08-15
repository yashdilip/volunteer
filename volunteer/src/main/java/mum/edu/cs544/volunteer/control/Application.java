package mum.edu.cs544.volunteer.control;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import mum.edu.cs544.volunteer.configuration.JPAUtil;
import mum.edu.cs544.volunteer.domain.Address;
import mum.edu.cs544.volunteer.domain.Project;
import mum.edu.cs544.volunteer.domain.Role;
import mum.edu.cs544.volunteer.domain.Task;
import mum.edu.cs544.volunteer.domain.User;
import mum.edu.cs544.volunteer.service.IService;
import mum.edu.cs544.volunteer.service.ServiceImpl;

public class Application {
	
	public static void main(String[] args) {
		IService service = new ServiceImpl();
		
		System.out.println("Creating User...");
		User u = new User();
		Address address = new Address();
		address.setStreet("1000N 4th ST");
		address.setCity("FairField");
		address.setState("IA");
		address.setZip("52557");
		
		u.setName("Dilip Nepali");
		u.setEmail("dilip@abc.com");
		u.setPassword("dilip123");
		u.setRole(Role.VOLUNTEER);
		u.setAddress(address);
		
		System.out.println("Creating new Project...");
		Project p = new Project();
		p.setProjectname("Volunteering application");
		p.setProjectStatus("incompleted");
		p.setDescription("it will help to organize volunteering service");
		p.setLocation("FairField, IA");
		p.setStartDate("2016-08-01");
		p.setEndDate("2016-08-20");
		
		System.out.println("creating task of the project "+p.getProjectname());
		Task t1 = new Task();
		t1.setDescription("entity creation");
		t1.setResourceRequired("JPA, Hibernate");
		t1.setTaskStatus("incompleted");
		t1.setTimeframeToCompleteInDays(1.5);
		
		Task t2 = new Task();
		t2.setDescription("database mapping");
		t2.setResourceRequired("MYSQL");
		t2.setTaskStatus("incomplete");
		t2.setTimeframeToCompleteInDays(0.5);
		p.setTasks(Arrays.asList(t1,t2));
		
		
		/*EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(u);
			em.persist(address);
			em.persist(p);
			em.persist(t1);
			em.persist(t2);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}*/
		
		
		service.createUser(u);
		//service.saveUserAddress(address);
		//service.createNewProject(p);
		//service.saveProjectTask(t1);
		//service.saveProjectTask(t2);
		
		System.out.println("Test");
		
		
		
	}
	

}
