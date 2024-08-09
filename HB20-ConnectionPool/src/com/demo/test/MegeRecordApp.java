package com.demo.test;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.Student;
import com.demo.util.HibernateUtil;

public class MegeRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Student std = null;

		try {

			Student student = new Student();
			student.setSid(1);
			student.setSname("tendulkar");
			student.setSage(52);
			student.setSaddr("MI");

			session = HibernateUtil.getSession();

			if (session != null) {
				transaction = session.beginTransaction();
			}

			if (transaction != null) {

				std = (Student) session.merge(student);
				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object inserted to  the database with the id :: " + std);
			} else {
				transaction.rollback();
				System.out.println("Object not inserted to the database...");
			}

			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
