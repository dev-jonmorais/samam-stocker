package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lp3b_sio.jpa2.dao.SupplierDAO;
import com.lp3b_sio.jpa2.model.Supplier;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ReadSupplierBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	SupplierDAO supplierDAO;
	
	private List<Supplier> suppliers = new ArrayList<>();
	
	/**
	 * GET SUPPLIERS
	 */
	public List<Supplier> getSuppliers() {
		return suppliers;
	}
	
	private Supplier supplierSelected;
	
	/**
	 * GET SUPPLIER SELECTED
	 */
	public Supplier getSupplierSelected() {
		return supplierSelected;
	}
	/**
	 * SET SUPPLIER SELECTED
	 */
	public void setSupplierSelected(Supplier supplierSelected) {
		this.supplierSelected = supplierSelected;
	}
	
	/**
	 * DELETE SUPPLIER SELECTED
	 */
	public void delete() {
		try {
			supplierDAO.delete(supplierSelected);
			this.suppliers.remove(supplierSelected);
			FacesUtil.addSuccessMessage("Supplier Name " + supplierSelected.getSupplierName() + " deleted successfully.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**
	 * INIT SUPPLIER
	 */
	@PostConstruct
	public void init() {
		suppliers = supplierDAO.allSuppliers();
	}

}
