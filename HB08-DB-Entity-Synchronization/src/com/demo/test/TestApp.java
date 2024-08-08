package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.demo.model.Student;
import com.demo.util.HibernateUtil;

public class TestApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		int id = 18;
		try {
			session = HibernateUtil.getSession();
			Student student = session.get(Student.class, id);
			System.out.println("Before updation in table: " + student);

			if (session != null) {
				System.in.read();
				session.refresh(student);
				System.out.println("After updation in table: " + student);

			} else {
				System.out.println("Record available for the given id :: " + id);
			}

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
