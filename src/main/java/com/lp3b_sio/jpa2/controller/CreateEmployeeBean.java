package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lp3b_sio.jpa2.model.Employee;
import com.lp3b_sio.jpa2.service.EmployeeService;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CreateEmployeeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmployeeService employeeService;
	
//	@Inject
//	private EmployeeDAO employeeDAO;
	
	private Employee employee;
	private List<Employee> employees;
	
	/**
	 * GET EMPLOYEE
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	/**
	 * SET EMPLOYEE
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	/**
	 * SAVE EMPLOYEE
	 */
	public void save() {
		try {
			this.employeeService.save(employee);
			FacesUtil.addSuccessMessage("Employee saved successfully!");
			
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
		//this.cities = this.cityDAO.allCities();
	}
	
	/**
	 * CLEAR CUSTOMER
	 */
	public void clear() {
		this.employee = new Employee();
	}
	
	/**
	 * GET CITY
	 */
//	public List<City> getCities() {
//		return cities;
//	}
}
