package com.demo.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.dto.Student;
import com.demo.util.HibernateUtil;

//Persistence logic using JDBC API
public class StudentDaoImpl implements IStudentDao {

	Session session = HibernateUtil.getSession();

	@Override
	public String addStudent(String sname, Integer sage, String saddress) {

		Transaction transaction = session.beginTransaction();
		boolean flag = false;
		String status = "";

		try {
			if (transaction != null) {
				Student student = new Student();
				student.setSname(sname);
				student.setSage(sage);
				student.setSaddress(saddress);

				session.save(student);
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				status = "success";
			} else {
				transaction.rollback();
				status = "failure";
			}
		}

		return status;
	}

	@Override
	public Student searchStudent(Integer sid) {

		Student student = session.get(Student.class, sid);
		if (student != null) {
			return student;
		} else {
			return null;
		}

	}

	@Override
	public String deleteStudent(Integer sid) {
		
		Transaction transaction = session.beginTransaction();
		boolean flag = false;
		String status = "";
		
		try {
			if (transaction != null) {
				Student student = session.get(Student.class, sid);
				
				if (student != null) {
					session.delete(student);
					flag = true;
				} else {
					return "not found";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				status = "success";
			} else {
				transaction.rollback();
				status = "failure";
			}
		}

		return status;

	}

	@Override
	public String updateStudent(Student student) {
		
		
		return "failure";
	}

}
