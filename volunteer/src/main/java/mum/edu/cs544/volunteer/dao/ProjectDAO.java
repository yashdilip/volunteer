package mum.edu.cs544.volunteer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;


import mum.edu.cs544.volunteer.configuration.JPAUtil;
import mum.edu.cs544.volunteer.domain.Project;

public class ProjectDAO implements IProjectDAO {

	public void createProject(Project project) {

		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;

		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(project);
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

	public List<Project> getAllProjects() {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		List<Project> projects= null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("FROM Project");
			projects = query.getResultList();
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
		return projects;
	}

	public List<Project> getAllCompletedProjects() {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		List<Project> projects= null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("FROM Project p WHERE p.projectStatus = :status");
			query.setParameter("status", "completed");
			projects = query.getResultList();
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
		return projects;
	}

	public List<Project> getAllIncompletedProjects() {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		List<Project> projects= null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("FROM Project p WHERE p.projectStatus = :status");
			query.setParameter("status", "incompleted");
			projects = query.getResultList();
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
		return projects;
	}

	public List<Project> searchProjectByResource(String resource) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		List<Project> projects= null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("FROM Project p JOIN p.tasks t WHERE t.resourceRequired like :skill");
			query.setParameter("skill","%"+resource+"%");
			projects = query.getResultList();
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
		return projects;
	}

	public List<Project> searchProjectByKeyword(String keyword) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		List<Project> projects= null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("FROM Project p WHERE p.projectname like :key");
			query.setParameter("key", "%"+keyword+"%");
			projects = query.getResultList();
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
		return projects;
	}

	public List<Project> searchProjectByLocation(String location) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		List<Project> projects= null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("FROM Project p WHERE p.location like :location");
			query.setParameter("location", "%"+location+"%");
			projects = query.getResultList();
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
		return projects;
	}
	public void updateProject(Project project) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Project p = new Project();
			Query query = em.createQuery("FROM Project p WHERE p.projectId =:projectId");
			query.setParameter("projectId", project.getProjectId());
			p = (Project) query.getSingleResult();
			em.remove(p);
			em.persist(project);
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

	public List<Project> getAllProjectsHavingVolunteer() {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		List<Project> projects= null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("FROM Project p JOIN p.tasks t WHERE COUNT(p.users)>=1 ORDER BY t.timeframeToCompleteInDays");
			projects = query.getResultList();
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
		return projects;
	}

}
