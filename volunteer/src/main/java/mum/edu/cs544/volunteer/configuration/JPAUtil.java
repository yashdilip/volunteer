package mum.edu.cs544.volunteer.configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import mum.edu.cs544.volunteer.control.Application;

public class JPAUtil {
/*	final static Logger logger = Logger.getLogger(Application.class);
*/	
	private static final EntityManagerFactory emf;
	static {
		try {
			emf = Persistence.createEntityManagerFactory("volunteer");
		} catch (Throwable ex) {
/*			logger.error("Initial EntityManagerFactory creation Failed", ex);
*/			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager(){
		EntityManager em = emf.createEntityManager();
		return em;
	}
}
