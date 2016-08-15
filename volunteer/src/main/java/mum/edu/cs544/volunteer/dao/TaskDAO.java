package mum.edu.cs544.volunteer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mum.edu.cs544.volunteer.configuration.JPAUtil;
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
		try {
			tx = em.getTransaction();
			tx.begin();
			Task t = new Task();
			Query query = em.createQuery("FROM Project p WHERE p.projectId =:projectId");
			query.setParameter("projectId", task.getTaskId());
			t = (Task) query.getSingleResult();
			em.remove(t);
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

}
