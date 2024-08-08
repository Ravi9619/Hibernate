package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.Student;
import com.demo.util.HibernateUtil;

public class UpdateRecord1 {

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
					student.setSid(18);
					student.setSname("virat");
					student.setSaddress("IND");
					student.setSage(36);
					session.update(student);
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
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
