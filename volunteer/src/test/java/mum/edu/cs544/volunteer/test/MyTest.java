package mum.edu.cs544.volunteer.test;

import static org.junit.Assert.*;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mum.edu.cs544.volunteer.domain.Address;
import mum.edu.cs544.volunteer.domain.Beneficiary;
import mum.edu.cs544.volunteer.domain.Project;
import mum.edu.cs544.volunteer.domain.Role;
import mum.edu.cs544.volunteer.domain.Task;
import mum.edu.cs544.volunteer.domain.User;
import mum.edu.cs544.volunteer.service.IService;
import mum.edu.cs544.volunteer.service.ServiceImpl;

public class MyTest {
	IService service = new ServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		/*for image upload*/
		Path path = FileSystems.getDefault().getPath("C:\\tmp", "star_trek.jpg");
		
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
		t1.setImage(Files.readAllBytes(path));
		
		Task t2 = new Task();
		t2.setDescription("database mapping");
		t2.setResourceRequired("MYSQL");
		t2.setTaskStatus("incomplete");
		t2.setTimeframeToCompleteInDays(0.5);
		p.setTasks(Arrays.asList(t1,t2));
		t2.setImage(Files.readAllBytes(path));
		
		System.out.println("creating beneficiary");
		Beneficiary b = new Beneficiary();
		b.setBeneficiaryName("MUM");
		b.setBeneficiaryDescription("This project will be used for future students");
		b.setProjects(Arrays.asList(p));
		b.setImage(Files.readAllBytes(path));
		p.setBeneficiaries(Arrays.asList(b));
		
		/*persisting into database*/
		service.saveUserAddress(address);
		service.createUser(u);
		service.saveProjectTask(t1);
		service.saveProjectTask(t2);
		service.createBeneficiary(b);
		service.createNewProject(p);
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUserDAO(){
		assertEquals("Dilip Nepali", service.getAllUsers().get(0).getName());
	}
	@Test
	public void testProjectDAO(){
		assertEquals("Volunteering application",service.getAllProjects().get(0).getProjectname());
	}
}
