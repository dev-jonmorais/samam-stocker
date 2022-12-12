package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lp3b_sio.jpa2.dao.CityDAO;
import com.lp3b_sio.jpa2.model.City;
import com.lp3b_sio.jpa2.model.Supplier;
import com.lp3b_sio.jpa2.service.SupplierService;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CreateSupplierBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SupplierService supplierService;
	
	@Inject
	private CityDAO cityDAO;
	
	private Supplier supplier;
	private List<City> cities;
	
	/**
	 * GET SUPPLIER
	 */
	public Supplier getSupplier() {
		return supplier;
	}
	
	/**
	 * SET SUPPLIER
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	/**
	 * SAVE SUPPLIER
	 */
	public void save() {
		try {
			this.supplierService.save(supplier);
			FacesUtil.addSuccessMessage("Supplier saved successfully!");
			
			this.clear();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**
	 * INIT SUPPLIER
	 */
	@PostConstruct
	public void init() {
		this.clear();
		this.cities = this.cityDAO.allCities();
	}
	
	/**
	 * CLEAR SUPPLIER
	 */
	public void clear() {
		this.supplier = new Supplier();
	}
	
	/**
	 * GET CITY
	 */
	public List<City> getCities() {
		return cities;
	}
}
