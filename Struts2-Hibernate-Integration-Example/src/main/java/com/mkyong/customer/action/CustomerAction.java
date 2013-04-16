package com.mkyong.customer.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mkyong.customer.model.Customer;
import com.mkyong.listener.HibernateListener;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
 
public class CustomerAction extends ActionSupport 
	implements ModelDriven{

	Customer customer = new Customer();
	List<Customer> customerList = new ArrayList<Customer>();
	
	public String execute() throws Exception {
		return SUCCESS;
	}

	public Object getModel() {
		return customer;
	}
	
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	//save customer
	public String addCustomer() throws Exception{
		
		//get hibernate session from the servlet context
		SessionFactory sessionFactory = 
	         (SessionFactory) ServletActionContext.getServletContext()
                     .getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		//save it
		customer.setCreatedDate(new Date());
	 
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();
	 
		//reload the customer list
		customerList = null;
		customerList = session.createQuery("from Customer").list();
		
		return SUCCESS;
	
	}
	
	//list all customers
	public String listCustomer() throws Exception{
		
		//get hibernate session from the servlet context
		SessionFactory sessionFactory = 
	         (SessionFactory) ServletActionContext.getServletContext()
                     .getAttribute(HibernateListener.KEY_NAME);
		System.out.println("sessionFactory > " + sessionFactory);
		Session session = sessionFactory.openSession();

		customerList = session.createQuery("from Customer").list();
		
		return SUCCESS;
	
	}
	
}