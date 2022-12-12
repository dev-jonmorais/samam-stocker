package com.lp3b_sio.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.lp3b_sio.jpa2.dao.SupplierDAO;
import com.lp3b_sio.jpa2.model.Supplier;
import com.lp3b_sio.jpa2.util.jpa.Transactional;

public class SupplierService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SupplierDAO supplierDAO;
	
	@Transactional
	public void save(Supplier supplier) throws NegocioException {
		if ( supplier.getSupplierName() == null || supplier.getSupplierName().trim().equals("") ) { 
			throw new NegocioException("The SUPPLIER NAME cannot be empty!");
		}
		
		if ( supplier.getDocCNPJ() == null || supplier.getDocCNPJ().trim().equals("") ) { 
			throw new NegocioException("The DOCUMENT CNPJ cannot be empty!");
		}
		
		if ( supplier.getPhoneMain() == null || supplier.getPhoneMain().trim().equals("") ) { 
			throw new NegocioException("The PHONE MAIN cannot be empty!");
		}
		
		this.supplierDAO.saveSupplier(supplier);
	}
}
