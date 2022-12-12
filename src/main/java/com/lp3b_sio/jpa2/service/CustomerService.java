package com.lp3b_sio.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.lp3b_sio.jpa2.dao.CustomerDAO;
import com.lp3b_sio.jpa2.model.Customer;
import com.lp3b_sio.jpa2.util.jpa.Transactional;

public class CustomerService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustomerDAO customerDAO;
	
	@Transactional
	public void save(Customer customer) throws NegocioException {
		if ( customer.getFirstName() == null || customer.getFirstName().trim().equals("") ) { 
			throw new NegocioException("The FIRST NAME cannot be empty!");
		}
		
		if ( customer.getLastName() == null || customer.getLastName().trim().equals("") ) { 
			throw new NegocioException("The LAST NAME cannot be empty!");
		}
		
		if ( customer.getDocCPF() == null || customer.getDocCPF().trim().equals("") ) { 
			throw new NegocioException("The DOCUMENT CPF cannot be empty!");
		}
		
		if ( customer.getDocCNPJ() == null || customer.getDocCNPJ().trim().equals("") ) { 
			throw new NegocioException("The DOCUMENT CNPJ cannot be empty!");
		}
		
		if ( customer.getPhoneMain() == null || customer.getPhoneMain().trim().equals("") ) { 
			throw new NegocioException("The PHONE MAIN cannot be empty!");
		}
		
//		if ( customer.getCityName() == null || customer.getCityName().trim().equals("") ) { 
//			throw new NegocioException("The CITY NAME is not empty!");
//		}
		
//		if ( customer.getNeighboorhood() == null || customer.getNeighboorhood().trim().equals("") ) { 
//			throw new NegocioException("The NEIGHBOORHOOD is not empty!");
//		}
		
		this.customerDAO.saveCustomer(customer);
	}
}
