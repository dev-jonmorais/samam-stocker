package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lp3b_sio.jpa2.dao.CustomerDAO;
import com.lp3b_sio.jpa2.model.Customer;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ReadCustomerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	CustomerDAO customerDAO;
	
	private List<Customer> customers = new ArrayList<>();
	
	/**
	 * GET CUSTOMERS
	 */
	public List<Customer> getCustomers() {
		return customers;
	}
	
	private Customer customerSelected;
	
	/**
	 * GET CUSTOMER SELECTED
	 */
	public Customer getCustomerSelected() {
		return customerSelected;
	}
	/**
	 * SET CUSTOMER SELECTED
	 */
	public void setCustomerSelected(Customer customerSelected) {
		this.customerSelected = customerSelected;
	}
	
	/**
	 * DELETE CUSTOMER SELECTED
	 */
	public void delete() {
		try {
			customerDAO.delete(customerSelected);
			this.customers.remove(customerSelected);
			FacesUtil.addSuccessMessage("Customer Name " + customerSelected.getFirstName() + " deleted successfully.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**
	 * INIT CUSTOMER
	 */
	@PostConstruct
	public void init() {
		customers = customerDAO.allCustomers();
	}

}
