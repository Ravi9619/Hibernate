package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.model.Student;

public class XMLJavaApp {

	public static void main(String[] args) throws Exception {

		SessionFactory sessionFactory = null;
		Session session = null;
		int id = 18;
		try {

			Configuration cfg = new Configuration();
			cfg.configure();

			cfg.addAnnotatedClass(Student.class);

			sessionFactory = cfg.buildSessionFactory();

			session = sessionFactory.openSession();

			Student student = session.get(Student.class, id);

			if (session != null) {
				System.out.println("Before updation in table: " + student);

			} else {
				System.out.println("Record not available for the given id :: " + id);
			}

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}
