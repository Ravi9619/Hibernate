package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.Student;
import com.demo.util.HibernateUtil;

public class DeleteRecordApp2 {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {
			session = HibernateUtil.getSession();
			int id = 18;
			Student student = session.get(Student.class, id);

			if (session != null) {
				transaction = session.beginTransaction();

				if (transaction != null) {
					if (student != null) {
						session.delete(student);
						flag = true;
					} else {
						System.out.println("Record not available for deletion:: ");
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
				System.out.println("Object deleted from database: ");
			} else {
				transaction.rollback();
				System.out.println("Object not deleted from database: ");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
