package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lp3b_sio.jpa2.dao.ProductDAO;
import com.lp3b_sio.jpa2.model.Inventory;
import com.lp3b_sio.jpa2.model.Product;
import com.lp3b_sio.jpa2.service.InventoryService;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CreateInventoryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Inventory inventory;
	private List<Product> products;
	
	@Inject
	private ProductDAO productDAO;
	
	@Inject
	private InventoryService inventoryService;
	
	@PostConstruct
	public void init() {
		this.clear();
		this.products = productDAO.allProducts();
	}
	
	public void save() {
		try {
			this.inventoryService.save(inventory);
			FacesUtil.addSuccessMessage("Inventory saved successfully!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador");
		}
		
		this.clear();
	}
	
	public void clear() {
		this.inventory = new Inventory();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public List<Product> getProducts() {
		return products;
	}
}
