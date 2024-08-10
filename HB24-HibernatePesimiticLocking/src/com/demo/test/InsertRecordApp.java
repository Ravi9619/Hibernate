package com.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.InsurancePolicy;
import com.demo.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		Integer id = null;
		boolean flag = false;		

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				InsurancePolicy policy = new InsurancePolicy();
				policy.setPname("Jeevan Anand");
				policy.setPtype("monthly");
				policy.setTenure(12);
				id  = (Integer) session.save(policy);
				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object inserted to  the database with the id :: "+id);
			} else {
				transaction.rollback();
				System.out.println("Object not inserted to the database...");
			}

			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
