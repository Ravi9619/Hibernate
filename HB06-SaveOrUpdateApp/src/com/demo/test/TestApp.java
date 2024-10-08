package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.Student;
import com.demo.util.HibernateUtil;

public class TestApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {

			session = HibernateUtil.getSession();

			if (session != null) {
				transaction = session.beginTransaction();

				if (transaction != null) {
					Student student = new Student();
					student.setSid(99);
					student.setSname("Gayle");
					student.setSaddress("KKR");
					student.setSage(41);
					session.saveOrUpdate(student);
					flag = true;

				}
			}

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				System.in.read();
				transaction.commit();
				System.out.println("Object saved to database: ");
			} else {
				transaction.rollback();
				System.out.println("Object not updated to database: ");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
