package com.demo.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.BankAccount;
import com.demo.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		Long id = null;
		boolean flag = false;

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				BankAccount account = new BankAccount();
				
				account.setBalance(2343.5);
				account.setHolderName("sachin");
				account.setType("savings");
				
				id = (Long) session.save(account);
				
				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object inserted to  the database with the id :: " + id);
			} else {
				transaction.rollback();
				System.out.println("Object not inserted to the database...");
			}

			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
