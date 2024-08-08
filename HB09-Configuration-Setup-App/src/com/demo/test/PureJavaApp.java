package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.model.Student;

public class PureJavaApp {

	public static void main(String[] args) throws Exception {

		SessionFactory sessionFactory = null;
		Session session = null;
		int id = 18;
		try {

			Configuration cfg = new Configuration();
			cfg.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
			cfg.setProperty("hibernate.connection.url", "jdbc:mysql:///testdb");
			cfg.setProperty("hibernate.connection.username", "root");
			cfg.setProperty("hibernate.connection.password", "Root12345@");

			cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
			cfg.setProperty("hibernate.show_sql", "true");
			cfg.setProperty("hibernate.format_sql", "true");
			cfg.setProperty("hibernate.hbm2ddl.auto", "update");

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
