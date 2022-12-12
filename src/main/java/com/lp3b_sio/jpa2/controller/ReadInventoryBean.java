package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lp3b_sio.jpa2.dao.InventoryDAO;
import com.lp3b_sio.jpa2.model.Inventory;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ReadInventoryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InventoryDAO inventoryDAO;
	
	private List<Inventory> inventories = new ArrayList<>();
	
	public List<Inventory> getInventories() {
		return inventories;
	}
	
	private Inventory inventorySelected;

	public Inventory getInventorySelected() {
		return inventorySelected;
	}

	public void setInventorySelected(Inventory inventorySelected) {
		this.inventorySelected = inventorySelected;
	}
	
	/**
	 * DELETE INVENTORY SELECTED
	 */
	public void delete() {
		try {
			inventoryDAO.delete(inventorySelected);
			this.inventories.remove(inventorySelected);
			FacesUtil.addSuccessMessage("Inventory Item Name " + inventorySelected.getItemName() + " deleted successfully.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**
	 * INIT INVENTORY
	 */
	@PostConstruct
	public void init() {
		inventories = inventoryDAO.allInventories();
	}
}
