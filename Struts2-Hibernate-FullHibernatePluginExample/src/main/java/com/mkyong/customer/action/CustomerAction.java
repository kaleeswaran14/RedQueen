package com.mkyong.customer.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mkyong.customer.dao.CustomerDAO;
import com.mkyong.customer.dao.impl.CustomerDAOImpl;
import com.mkyong.customer.model.Customer;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
 
public class CustomerAction extends ActionSupport 
	implements ModelDriven{

	Customer customer = new Customer();
	List<Customer> customerList = new ArrayList<Customer>();
	CustomerDAO customerDAO = new CustomerDAOImpl();
	
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
		
		//save it
		customer.setCreatedDate(new Date());
		customerDAO.addCustomer(customer);
	 
		//reload the customer list
		customerList = null;
		customerList = customerDAO.listCustomer();
		
		return SUCCESS;
	
	}
	
	//list all customers
	public String listCustomer() throws Exception{
		
		customerList = customerDAO.listCustomer();
		
		return SUCCESS;
	
	}
	
}