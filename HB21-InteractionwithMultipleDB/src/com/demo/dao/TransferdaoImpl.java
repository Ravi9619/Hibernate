package com.demo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.model.Product;
import com.demo.util.MySQLHibernateUtil;
import com.demo.util.OracleHibernateUtil;

public class TransferdaoImpl implements ITransferdao {

	@Override
	public String transferProductById(Integer id) {

		Session oracleSession = OracleHibernateUtil.getSession();
		Session mysqlSession = MySQLHibernateUtil.getSession();
		Integer idValue = 0;
		Boolean flag = false;

		Transaction mysqlTransaction = null;

		Product product = oracleSession.get(Product.class, id);

		if (product == null) {
			return "Record not available for copying...";
		} else {
			try {
				mysqlTransaction = mysqlSession.beginTransaction();
				mysqlSession.save(product);
				flag = true;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (flag) {
					mysqlTransaction.commit();
					return "Data copied from oracle to mysql with id :: " + idValue;
				} else {
					mysqlTransaction.rollback();
					return "Data not copied from oracle to mysql with id :: " + idValue;
				}
			}

		}

	}
}
