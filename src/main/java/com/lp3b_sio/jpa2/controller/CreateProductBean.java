package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import com.lp3b_sio.jpa2.dao.SupplierDAO;
import com.lp3b_sio.jpa2.model.Category;
import com.lp3b_sio.jpa2.model.Product;
import com.lp3b_sio.jpa2.model.Supplier;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.service.ProductService;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CreateProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Product product;
	private List<Supplier> suppliers;
	private List<Category> categories;
	
	@Inject
	private SupplierDAO supplierDAO;
	
	@Inject
	private ProductService productService;
	
	private UploadedFile uploadedFile;
	
	@PostConstruct
	public void init() {
		this.clear();		
		this.suppliers = supplierDAO.allSuppliers();
		this.categories = Arrays.asList(Category.values());
	}
	
	public void save() {
		try {
			if (this.uploadedFile != null) {
				this.product.setProductPhotoCover(this.uploadedFile.getContent());
			}
			
			this.productService.save(product);
			FacesUtil.addSuccessMessage("Product saved successfully!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido. Contatar o administrador");
		}
		
		this.clear();
	}
	
	public void clear() {
		this.product = new Product();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public List<Supplier> getSuppliers() {
		return suppliers;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
}
