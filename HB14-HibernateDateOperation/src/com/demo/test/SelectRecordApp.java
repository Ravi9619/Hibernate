package com.demo.test;

import javax.persistence.Id;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.PersonInfo;
import com.demo.util.HibernateUtil;

public class SelectRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;

		try {
			session = HibernateUtil.getSession();

			if (session != null) {
				int id = 10;
				PersonInfo info = session.get(PersonInfo.class, id);
				
				if (info != null) {
					System.out.println(info);
				} else {
					System.out.println("Record not available for given id:: "+id);
				}
				
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
