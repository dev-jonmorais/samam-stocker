package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lp3b_sio.jpa2.dao.EmployeeDAO;
import com.lp3b_sio.jpa2.model.Employee;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ReadEmployeeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	EmployeeDAO employeeDAO;
	
	private List<Employee> employees = new ArrayList<>();
	
	/**
	 * GET EMPLOYEES
	 */
	public List<Employee> getEmployees() {
		return employees;
	}
	
	private Employee employeeSelected;
	
	/**
	 * GET EMPLOYEE SELECTED
	 */
	public Employee getEmployeeSelected() {
		return employeeSelected;
	}
	/**
	 * SET EMPLOYEE SELECTED
	 */
	public void setEmployeeSelected(Employee employeeSelected) {
		this.employeeSelected = employeeSelected;
	}
	
	/**
	 * DELETE EMPLOYEE SELECTED
	 */
	public void delete() {
		try {
			employeeDAO.delete(employeeSelected);
			this.employees.remove(employeeSelected);
			FacesUtil.addSuccessMessage("Employee Name " + employeeSelected.getFirstName() + employeeSelected.getLastName() + " deleted successfully.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**
	 * INIT EMPLOYEE
	 */
	@PostConstruct
	public void init() {
		employees = employeeDAO.allEmployees();
	}

}
