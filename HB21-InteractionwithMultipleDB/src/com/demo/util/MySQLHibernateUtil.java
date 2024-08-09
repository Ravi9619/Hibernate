package com.demo.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.model.Product;

public class MySQLHibernateUtil {

	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	private MySQLHibernateUtil() {

	}

	static {
		sessionFactory = new Configuration().configure("com/demo/config/mysql-hibernate.cfg.xml")
				.addAnnotatedClass(Product.class)
				.buildSessionFactory();
	}

	public static Session getSession() {
		if (session == null)
			session = sessionFactory.openSession();
		return session;
	}

	public static void closeSession(Session session) {
		if (session != null) {
			session.close();
		}
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null)
			sessionFactory.close();

	}

}
