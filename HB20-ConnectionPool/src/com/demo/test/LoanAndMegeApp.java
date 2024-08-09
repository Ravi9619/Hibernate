package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.Student;
import com.demo.util.HibernateUtil;

public class LoanAndMegeApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Student std1 = null;
		Student std2 = null;
		Student std3 = null;

		try {

			session = HibernateUtil.getSession();
			std1 = session.get(Student.class, 3);
			System.out.println("After loading data into L1 cache :: " + std1);

			if (session != null) {
				transaction = session.beginTransaction();
			}

			if (transaction != null) {
				std2 = new Student();
				std2.setSid(5);
				std2.setSaddr("MI");
				std2.setSname("Suryakumaryadav");
				std2.setSage(32);

				std3 = (Student) session.merge(std2);
				System.out.println("After merging in L1 cache :: " + std3);
				System.out.println(std1.hashCode() + "::" + std2.hashCode() + "::" + std3.hashCode());
				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object inserted to  the database with the id :: ");
			} else {
				transaction.rollback();
				System.out.println("Object not inserted to the database...");
			}

			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
