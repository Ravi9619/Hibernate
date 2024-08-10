package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.InsurancePolicy;
import com.demo.util.HibernateUtil;

public class ClienApp1 {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		Integer id = null;
		Boolean flag = false;

		session = HibernateUtil.getSession();
		try {

			transaction = session.beginTransaction();

			InsurancePolicy policy = session.get(InsurancePolicy.class, 1, LockMode.UPGRADE_NOWAIT);
			System.out.println(policy);

			Thread.sleep(30000);

			policy.setTenure(30);
			flag = true;

		} catch (HibernateException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object inserted to database with id :: " + id);
			} else {
				transaction.rollback();
				System.out.println("Object not inserted to database");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
