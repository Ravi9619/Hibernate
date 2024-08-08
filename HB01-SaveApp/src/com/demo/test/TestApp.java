package com.demo.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.demo.model.Student;

public class TestApp {

	public static void main(String[] args) throws Exception {
		
		//1. Inform JVM to activate hibernate
		Configuration configuration = new Configuration();
		configuration.configure();
		
		// Creating SessionFactory object to hold many other objects required for HIBERNATE
		SessionFactory factory = configuration.buildSessionFactory();
		
		// Using SessionFactory Object, get only one session to perform our persistence operation
		Session session = factory.openSession();
		
		//Begin Transaction as operation is non-select
		Transaction transaction = session.beginTransaction();
		
		
		//Create persistence object
		Student student = new Student();
		student.setSid(10);
		student.setSname("sachin");
		student.setSage(20);;
		student.setSaddress("MI");
		
		
		//Perform Persistence operation using Entity/Model/POJO object
		session.save(student);
		
		System.in.read();
		
		// Generate quesry and send to database
		transaction.commit();
		
		System.out.println("Object saved to database");
		
		session.close();
		factory.close();
		
	}

}
