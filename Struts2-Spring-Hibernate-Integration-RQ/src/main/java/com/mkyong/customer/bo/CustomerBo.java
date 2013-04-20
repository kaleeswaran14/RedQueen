package com.mkyong.customer.bo;

import java.util.List;

import com.mkyong.customer.model.Customer;
 
public interface CustomerBo{
	
	void addCustomer(Customer customer);
	
	Customer getCustomer(Long customerId);
	
	void deleteCustomer(Long customerId);
	
	List<Customer> listCustomer();
	
}