package mum.edu.cs544.volunteer.dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mum.edu.cs544.volunteer.configuration.JPAUtil;
import mum.edu.cs544.volunteer.domain.Address;
import mum.edu.cs544.volunteer.domain.User;

public class UserDAO implements IUserDAO{

	public void create(User user) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(user);
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

	public boolean authenticateUser(User user) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		User u = new User();
		
		try {
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("FROM User u where u.userId = :userId");
			query.setParameter("userId",user.getUserId());
			u = (User) query.getSingleResult();
			if(u!=null){
				if(u.getPassword().equals(user.getPassword()))
				{
					return true;
				}
			}
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
		
		return false;
	}

	public void saveNewAddress(Address address) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(address);
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

	public List<User> getAllUsers() {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = null;
		List<User> ulist = new ArrayList<User>();
		
		try {
			tx = em.getTransaction();
			tx.begin();
			Query query = em.createQuery("FROM User u");
			ulist = query.getResultList();
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
		
		return ulist;
	}


}
