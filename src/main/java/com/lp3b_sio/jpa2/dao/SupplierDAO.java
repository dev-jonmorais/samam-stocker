package com.lp3b_sio.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.lp3b_sio.jpa2.model.Supplier;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jpa.Transactional;

public class SupplierDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public void saveSupplier(Supplier supplier) {
		em.merge(supplier);
	}
	
	@SuppressWarnings("unchecked")
	public List<Supplier> allSuppliers() {
		return em.createQuery("from Supplier").getResultList();
	}

	@Transactional
	public void delete(Supplier supplier) throws NegocioException {
		Supplier supplierTemp = em.find(Supplier.class, supplier.getSupplierID());
		em.remove(supplierTemp);
		em.flush();
	}
	
	public Supplier searchById(Long sID) {
		return em.find(Supplier.class, sID);
	}
}
