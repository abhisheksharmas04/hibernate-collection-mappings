package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
	private static SessionFactory sessionFactory;

	static {
		try {
			// configure object
			Configuration configuration = new Configuration();
			StandardServiceRegistryBuilder serviceRegistery = new StandardServiceRegistryBuilder();
			StandardServiceRegistry register = serviceRegistery.configure("cfg.xml").build();
			// build session factory
			sessionFactory = configuration.buildSessionFactory(register);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null)
			return sessionFactory;
		else
			throw new RuntimeException("Invalid Session Factory object creation");
	}

	public static Session getSession() {
		Session session = null;
		if (sessionFactory != null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	public static void closeSession(Session session) {
		if (session != null)
			session.close();
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null)
			sessionFactory.close();
	}
}
