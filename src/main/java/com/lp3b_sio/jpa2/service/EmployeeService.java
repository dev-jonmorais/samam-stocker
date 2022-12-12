package com.lp3b_sio.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.lp3b_sio.jpa2.dao.EmployeeDAO;
import com.lp3b_sio.jpa2.model.Employee;
import com.lp3b_sio.jpa2.util.jpa.Transactional;

public class EmployeeService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmployeeDAO employeeDAO;
	
	@Transactional
	public void save(Employee employee) throws NegocioException {
		if ( employee.getFirstName() == null || employee.getFirstName().trim().equals("") ) { 
			throw new NegocioException("The FIRST NAME cannot be empty!");
		}
		
		if ( employee.getLastName() == null || employee.getLastName().trim().equals("") ) { 
			throw new NegocioException("The LAST NAME cannot be empty!");
		}
		
		if ( employee.getDocCPF() == null || employee.getDocCPF().trim().equals("") ) { 
			throw new NegocioException("The DOCUMENT CPF cannot be empty!");
		}
		
		if ( employee.getDocRG() == null || employee.getDocRG().trim().equals("") ) { 
			throw new NegocioException("The DOCUMENT RG cannot be empty!");
		}
		
		if ( employee.getPhoneMain() == null || employee.getPhoneMain().trim().equals("") ) { 
			throw new NegocioException("The PHONE MAIN cannot be empty!");
		}
		
		this.employeeDAO.saveEmployee(employee);
	}
}
