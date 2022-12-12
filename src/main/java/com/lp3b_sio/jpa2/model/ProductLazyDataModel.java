package com.lp3b_sio.jpa2.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import com.lp3b_sio.jpa2.dao.ProductDAO;

public class ProductLazyDataModel extends LazyDataModel<Product> implements Serializable {

	private ProductDAO productDAO;
	
	public ProductLazyDataModel(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	/**@Override
	public List<Product> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<Product> products = this.productDAO.searchWithPaginator(first, pageSize);
		
		this.setRowCount(this.productDAO.totalProductsRegistered().intValue());
		
		return products;
	} */

	@Override
	public int count(Map<String, FilterMeta> filterBy) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		// PRODUCT LIST
		List<Product> products = this.productDAO.searchWithPaginator(first, pageSize);
		
		this.setRowCount(this.productDAO.totalProductsRegistered().intValue());
		
		return products;
	}
}
