package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.Student;
import com.demo.util.HibernateUtil;

public class LoadRecordApp {

	public static void main(String[] args) {

		Session session = null;
		int id = 18;
		try {

			session = HibernateUtil.getSession();

			if (session != null) {
				Student student = session.load(Student.class, id);
				
				if (student != null) {
					System.out.println("Student id:: "+student.getSid());
					
					System.in.read();
					
					System.out.println("STUDENT NAME IS :: "+student.getSname());
					System.out.println("STUDENT AGE IS :: "+student.getSage());
					System.out.println("STUDENT ADDRESS IS :: "+student.getSaddress());
					
				} else {
					System.out.println("Record not found for given id ::"+id);
				}
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
