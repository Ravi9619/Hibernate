package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.MobileCustomer;
import com.demo.util.HibernateUtil;

public class SelectRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		MobileCustomer customer = null;
		Transaction transaction = null;
		Boolean flag = false;

		try {
			session = HibernateUtil.getSession();

			if (session != null) {
				customer = session.get(MobileCustomer.class, 1);
				System.out.println("Loading the object :: " + customer);

				transaction = session.beginTransaction();
				customer.setCallerTune("Hello...");
				session.update(customer);
				flag = true;

			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object saved to database: ");
				System.out.println("After modification:: " + customer);
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
