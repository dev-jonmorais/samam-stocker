package com.lp3b_sio.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.lp3b_sio.jpa2.dao.InventoryDAO;
import com.lp3b_sio.jpa2.model.Inventory;
import com.lp3b_sio.jpa2.util.jpa.Transactional;

public class InventoryService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private InventoryDAO inventoryDAO;
	
	@Transactional
	public void save(Inventory inventory) throws NegocioException {
		if (inventory.getItemName() == null || inventory.getItemName().trim().equals("")) {
			throw new NegocioException("The ITEM NAME cannot be empty!");
		}
		
		if (inventory.getStockMin() == 00) {
			throw new NegocioException("The STOCK MIN cannot be empty!");
		}
		
		if (inventory.getStockMax() == 00) {
			throw new NegocioException("The STOCK MAX cannot be empty!");
		}
		
		if (inventory.getStockAvailable() == 00) {
			throw new NegocioException("The STOCK AVAILABLE cannot be empty!");
		}
		
		this.inventoryDAO.save(inventory);
	}
}
