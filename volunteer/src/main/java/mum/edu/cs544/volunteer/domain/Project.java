package mum.edu.cs544.volunteer.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Project {
	@Id
	@GeneratedValue
	private int projectId;
	private String projectname;
	private String description;
	private String location;
	//@Temporal(TemporalType.DATE)
	private String startDate;
	//@Temporal(TemporalType.DATE)
	private String endDate;
	
	@OneToMany
	@JoinTable(name="PROJECT_TASK", joinColumns = @JoinColumn(name="projectId"),
	inverseJoinColumns = @JoinColumn(name="taskId"))
	private List<Task> tasks = new ArrayList<Task>();
	
	@ManyToMany
	@JoinTable(name="USER_PROJECT", joinColumns=@JoinColumn(name="projectId"), 
	inverseJoinColumns=@JoinColumn(name="userId"))
	private List<User> users = new ArrayList<User>();
	
	private String projectStatus;
	
	@ManyToMany
	@JoinTable(name="PROJECT_BENEFICIARY", joinColumns = @JoinColumn(name="projectId"),
	inverseJoinColumns = @JoinColumn(name="beneficiaryId"))
	private List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>();

	public Project() {
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}

	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
