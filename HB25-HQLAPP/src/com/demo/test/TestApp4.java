package com.demo.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.demo.model.Product;
import com.demo.util.HibernateUtil;

public class TestApp4 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;

		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			Query<Object[]> query = session
					.createQuery("SELECT pname,price,qty from com.demo.model.Product WHERE pname IN(:prod1,:prod2)");

			// Set values to Named Parameter
			query.setParameter("prod1", "apple");
			query.setParameter("prod2", "samsung");

			// Execute Query
			List<Object[]> products = query.list();

			// process the list object
			System.out.println("PNAME\tPRICE\tQTY");
			products.forEach(row -> {
				for (Object object : row) {
					System.out.print(object + "\t");
				}
				System.out.println();
			});

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
