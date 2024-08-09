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

import com.demo.model.JobSeeker;
import com.demo.util.HibernateUtil;

public class InsertRecordApp {

	public static void main(String[] args) throws Exception {

		Session session = null;
		Transaction transaction = null;
		Integer id = null;
		boolean flag = false;
		
		byte[] photo = null;
		char[] resume = null;
		File f = null;
		
		try(FileInputStream fis = new FileInputStream("C:\\Users\\dubey\\Pictures\\goku.jpg")) {
			photo = new byte[fis.available()];
			fis.read(photo);
		} 
		
		//logic for copying the resume data to character array
		try {
			f = new File("C:\\Users\\dubey\\Documents\\resume.txt");
			try (FileReader fr = new FileReader(f)) {
				resume = new char[(int) f.length()];
				fr.read(resume);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			session = HibernateUtil.getSession();

			if (session != null)
				transaction = session.beginTransaction();

			if (transaction != null) {
				
				JobSeeker seeker = new JobSeeker();
				seeker.setJsName("ravi");
				seeker.setJsAddr("Mumbai");
				seeker.setPhoto(photo);
				seeker.setResume(resume);
				
				id = (Integer) session.save(seeker);
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
