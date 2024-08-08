package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.Student;
import com.demo.util.HibernateUtil;

public class SaveApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {

			session = HibernateUtil.getSession();

			if (session != null) {
				transaction = session.beginTransaction();
			}

			if (transaction != null) {

				Student student = new Student();
				student.setSid(18);
				student.setSname("kohli");
				student.setSage(35);
				student.setSaddress("RCB");

				session.save(student);
				flag = true;
			}

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("object saved to database...");
			} else {
				transaction.rollback();
				System.out.println("Object not saved to database...");
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
