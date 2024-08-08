package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.ProgrammerProjId;
import com.demo.model.ProgrammerProjInfo;
import com.demo.util.HibernateUtil;

public class SelectRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;

		try {
			session = HibernateUtil.getSession();

			if (session != null) {
				
				ProgrammerProjId id = new ProgrammerProjId();
				id.setPid(100);
				id.setProjId(502);
				
				ProgrammerProjInfo projInfo = session.get(ProgrammerProjInfo.class, id);
				if (projInfo != null) {
					System.out.println(projInfo);
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
