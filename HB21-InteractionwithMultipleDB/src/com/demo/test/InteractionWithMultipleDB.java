package com.demo.test;

import com.demo.dao.ITransferdao;
import com.demo.dao.TransferdaoImpl;
import com.demo.util.MySQLHibernateUtil;
import com.demo.util.OracleHibernateUtil;

public class InteractionWithMultipleDB {

	public static void main(String[] args) throws Exception {
		
		ITransferdao dao = new TransferdaoImpl();
		System.out.println(dao.transferProductById(1));
		
		OracleHibernateUtil.closeSessionFactory();
		MySQLHibernateUtil.closeSessionFactory();
		
	}

}
