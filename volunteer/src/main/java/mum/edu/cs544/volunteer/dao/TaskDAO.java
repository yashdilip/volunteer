package mum.edu.cs544.volunteer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mum.edu.cs544.volunteer.configuration.JPAUtil;
import mum.edu.cs544.volunteer.domain.Project;
import mum.edu.cs544.volunteer.domain.Task;

public class TaskDAO implements ITaskDAO{

	public List<Task> getAllTaskByProjectId(int projectId) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		List<Task> tasks= null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("SELECT p.task FROM Project p WHERE p.projectId= :id");
			query.setParameter("id", projectId);
			tasks = query.getResultList();
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}		
		return tasks;
	}

	public void updateTask(Task task) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		Task t = new Task();
		try {
			tx = em.getTransaction();
			tx.begin();
			Query q = em.createQuery("FROM Task t WHERE t.taskId=:tid");
			q.setParameter("tid",task.getTaskId());
			t = (Task)q.getSingleResult();
			t.setUser(task.getUser());
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Task> getAllTaskOfferedByVolunteer() {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		List<Task> tasks= null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("SELECT p.task FROM Project p JOIN p.tasks t WHERE COUNT(t.user)>=1 ORDER BY t.timeframeToCompleteInDays");
			tasks = query.getResultList();
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}		
		return tasks;
	}

	public void createTask(Task task) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(task);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
	}

	public List<Task> getTaskBySkill(String skill) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		List<Task> tlist = new ArrayList<Task>();
		try {
			tx = em.getTransaction();
			tx.begin();
			
			Query query = em.createQuery("SELECT p.tasks FROM Project p JOIN p.tasks t WHERE t.resourceRequired like :skill");
			query.setParameter("skill", "%"+skill+"%");
			tlist = query.getResultList();			
			tx.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return tlist;
	}		

}
