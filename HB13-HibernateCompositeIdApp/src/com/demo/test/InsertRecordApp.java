package com.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.ProgrammerProjId;
import com.demo.model.ProgrammerProjInfo;
import com.demo.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		ProgrammerProjId pk = null;

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				
				ProgrammerProjId projId = new ProgrammerProjId();
				projId.setPid(100);
				projId.setProjId(502);
				
				ProgrammerProjInfo projInfo = new ProgrammerProjInfo();
				projInfo.setId(projId);
				projInfo.setDeptNo(10);
				projInfo.setPname("sachin");
				projInfo.setProjName("IND");
				
			 	pk = (ProgrammerProjId) session.save(projInfo);
				
				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object inserted to  the database with the id :: " + pk);
			} else {
				transaction.rollback();
				System.out.println("Object not inserted to the database...");
			}

			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
