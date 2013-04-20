package com.mkyong.customer.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.mkyong.customer.bo.CustomerBo;
import com.mkyong.customer.model.Customer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction {

	private Long customerId;
	Customer customer = new Customer();
	List<Customer> customerList = new ArrayList<Customer>();
	
	CustomerBo customerBo;
	//DI via Spring
	public void setCustomerBo(CustomerBo customerBo) {
		this.customerBo = customerBo;
	}

//	public Object getModel() {
//		return customer;
//	}
	
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
		customerBo.addCustomer(customer);
	 
		//reload the customer list
		customerList = null;
		customerList = customerBo.listCustomer();
		
		return "success";
	
	}
	
	//list all customers
	public String listCustomer() throws Exception{
		
		customerList = customerBo.listCustomer();
		
		return "success";
	
	}

	//delete a customer
	public String deleteCustomer() throws Exception{
		System.out.println("deleet customerId > " + getCustomerId());
		customerBo.deleteCustomer(getCustomerId());
		customerList = customerBo.listCustomer();
		return "success";
	
	}
	
	//edit a customer
	public String editCustomer() throws Exception{
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		System.out.println("edit customerId > " + getCustomerId());
//		System.out.println("customerId > " + request.getParameter("id").toString());
		customer = customerBo.getCustomer(getCustomerId());
		System.out.println("edit customer name => " +  customer.getName());
		System.out.println("edit customer id => " +  customer.getCustomerId());
		customerList = customerBo.listCustomer();
		return "success";
	
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}