package com.lp3b_sio.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.lp3b_sio.jpa2.model.Category;
import com.lp3b_sio.jpa2.model.Product;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jpa.Transactional;

public class ProductDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Product searchById(Long pID) {
		return manager.find(Product.class, pID);
	}
	
	public void save(Product product) {
		manager.merge(product);
	}

	@SuppressWarnings("unchecked")
	public List<Product> allProducts() {
		return manager.createNamedQuery("Product.getAllProducts").getResultList();
	}
	
	@Transactional
	public void delete(Product product) throws NegocioException {
		product = searchById(product.getProductID());
		
		try {
			manager.remove(product);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Product is not possible deleted.");
		}
	}

	/**
	 * 
	 * LIST PRODUCT OF BEAUTY
	 * 
	 * @param category
	 * @return all products of the Beauty
	 * 
	 */
	public Product searchProductByCategoryOfBeauty(Category category) { 
		return manager.createNamedQuery("Product.getProductByCategory", Product.class)
				.setParameter("category", category.Beauty)
				.getSingleResult();
	}
	
	/**
	 * 
	 * LIST PRODUCT OF CLOTHING
	 * 
	 * @param category
	 * @return all products of the Clothing
	 * 
	 */
	public Product searchProductByCategoryOfClothing(Category category) { 
		return manager.createNamedQuery("Product.getProductByCategory", Product.class)
				.setParameter("category", category.Clothing)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Product> searchWithPaginator(int first, int pageSize) {
		return manager.createNamedQuery("Product.getAllProducts")
							.setFirstResult(first)
							.setMaxResults(pageSize)
							.getResultList();
	}

	public Long totalProductsRegistered() {
		return manager.createQuery("select count(p) from Product p", Long.class).getSingleResult();
	}
}
