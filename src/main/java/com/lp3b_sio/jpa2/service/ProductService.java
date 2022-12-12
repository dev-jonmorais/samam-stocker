package com.lp3b_sio.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.lp3b_sio.jpa2.dao.ProductDAO;
import com.lp3b_sio.jpa2.model.Product;
import com.lp3b_sio.jpa2.util.jpa.Transactional;

public class ProductService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProductDAO productDAO;
	
	@Transactional
	public void save(Product product) throws NegocioException {
		
		if (product.getProductName() == null || product.getProductName().trim().equals("")) {
			throw new NegocioException("The PRODUCT NAME cannot be empty!");
		}
		
		if (product.getPurchasePrice() == 0.00f) {
			throw new NegocioException("The PRODUCT PURCHASE PRICE cannot be empty!");
		}
		
		if (product.getSellingPrice() == 0.00f) {
			throw new NegocioException("The PRODUCT SELLING PRICE cannot be empty!");
		}
		
		if (product.getProductQuantity() == 0.0) {
			throw new NegocioException("The PRODUCT QUANTITY cannot be empty!");
		}
		
		this.productDAO.save(product);
	}

}
