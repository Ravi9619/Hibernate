package com.demo.test;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.demo.model.Product;
import com.demo.util.HibernateUtil;

public class TestApp5 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;

		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			Query<Product> query = session.createQuery("FROM com.demo.model.Product WHERE pid=:id");

			System.out.println("Enter id of product to be fetched :: ");
			int id = new Scanner(System.in).nextInt();

			// Set values to Named Parameter
			query.setParameter("id", id);

			// Execute Query
			List<Product> products = query.list();
			System.out.println(products.size());

			// process the list object
			if (!(products.isEmpty())) {
				Product product = products.get(0);
				System.out.println(product);
			} else {
				System.out.println("Record not available for given id:: " + id);
			}

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
