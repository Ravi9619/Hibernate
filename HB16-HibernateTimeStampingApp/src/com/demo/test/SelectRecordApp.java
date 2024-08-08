package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.BankAccount;
import com.demo.util.HibernateUtil;

public class SelectRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		BankAccount account = null;
		Boolean flag = false;
		Transaction transaction = null;

		try {

			session = HibernateUtil.getSession();
			Long id = 1L;
			account = session.get(BankAccount.class, id);
			System.out.println("Before modification ::" + account);

			if (account != null) {
				transaction = session.beginTransaction();
				account.setBalance(account.getBalance() + 1000);
				flag = true;
			} else {
				System.out.println("Record not available for given id :: " + id);
				return;
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object updated...");
				System.out.println("Account opening date :: " + account.getOpeningDate());
				System.out.println("Account lastly modified :: " + account.getLastUpdated());
				System.out.println("Account version count :: " + account.getCount());
			} else {
				transaction.rollback();
				System.out.println("Object failed to update... ");
			}
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
