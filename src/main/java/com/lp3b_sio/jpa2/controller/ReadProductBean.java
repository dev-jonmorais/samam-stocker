package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lp3b_sio.jpa2.dao.ProductDAO;
import com.lp3b_sio.jpa2.model.Product;
import com.lp3b_sio.jpa2.model.ProductLazyDataModel;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ReadProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	ProductDAO productDAO;
	
	private List<Product> products = new ArrayList<>();
	
	private ProductLazyDataModel lazyProducts;
	
	private Product productSelected;
	private Product productSelectedForDelete;
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void delete() {
		try {
			productDAO.delete(productSelectedForDelete);
			this.products.remove(productSelectedForDelete);
			FacesUtil.addSuccessMessage("Product " + productSelectedForDelete.getProductName() + " successfully deleted.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Product getProductSelected() {
		return productSelected;
	}
	public void setProductSelected(Product productSelected) {
		this.productSelected = productSelected;
	}

	public Product getProductSelectedForDelete() {
		return productSelectedForDelete;
	}
	public void setProductSelectedForDelete(Product productSelectedForDelete) {
		this.productSelectedForDelete = productSelectedForDelete;
	}

	@PostConstruct
	public void init() {
		//carros = carroDAO.buscarTodos();
		lazyProducts = new ProductLazyDataModel(productDAO);
	}
	
	public ProductLazyDataModel getLazyProducts() {
		return lazyProducts;
	}
}
