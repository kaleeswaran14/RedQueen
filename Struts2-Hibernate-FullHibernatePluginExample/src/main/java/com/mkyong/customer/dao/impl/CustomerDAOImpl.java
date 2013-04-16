package com.mkyong.customer.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mkyong.customer.dao.CustomerDAO;
import com.mkyong.customer.model.Customer;
 
public class CustomerDAOImpl implements CustomerDAO{
	
	@SessionTarget
	Session session;
	     
	@TransactionTarget
	Transaction transaction;
	
	//add the customer
	public void addCustomer(Customer customer){
		
		session.save(customer);
		
	}
	
	//return all the customers in list
	public List<Customer> listCustomer(){
		
		return session.createQuery("from Customer").list();
		
	}
	
}