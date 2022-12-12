package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lp3b_sio.jpa2.dao.CityDAO;
import com.lp3b_sio.jpa2.model.City;
import com.lp3b_sio.jpa2.model.Customer;
import com.lp3b_sio.jpa2.service.CustomerService;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CreateCustomerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private CityDAO cityDAO;
	
	private Customer customer;
	private List<City> cities;
	
	/**
	 * GET CUSTOMER
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * SET CUSTOMER
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * SAVE CUSTOMER
	 */
	public void save() {
		try {
			this.customerService.save(customer);
			FacesUtil.addSuccessMessage("Customer saved successfully!");
			
			this.clear();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**
	 * INIT CUSTOMER
	 */
	@PostConstruct
	public void init() {
		this.clear();
		this.cities = this.cityDAO.allCities();
	}
	
	/**
	 * CLEAR CUSTOMER
	 */
	public void clear() {
		this.customer = new Customer();
	}
	
	/**
	 * GET CITY
	 */
	public List<City> getCities() {
		return cities;
	}
}
