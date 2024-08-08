package com.demo.test;

import java.io.Serializable;

import javax.persistence.Id;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.Student;
import com.demo.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Integer id = null;

		try {
			session = HibernateUtil.getSession();

			if (session != null) {
				transaction = session.beginTransaction();

				if (transaction != null) {
					Student student = new Student();

					student.setSaddress("RCB");
					student.setSage(35);
					student.setSname("kohli");
					id = (Integer) session.save(student);
					flag = true;

				}
			}

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object saved to database with id: " + id);
			} else {
				transaction.rollback();
				System.out.println("Object not saved to database: ");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
