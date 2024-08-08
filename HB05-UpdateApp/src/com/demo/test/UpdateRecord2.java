package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.Student;
import com.demo.util.HibernateUtil;

public class UpdateRecord2 {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {

			session = HibernateUtil.getSession();
			Student student = session.get(Student.class, 18);

			if (session != null) {
				transaction = session.beginTransaction();

				if (transaction != null) {
					if (student != null) {
						System.out.println(student);
						System.out.println();

						student.setSaddress("RCB");
						session.update(student);
						flag = true;
					} else {
						System.out.println("Record not available for updation");
					}
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
