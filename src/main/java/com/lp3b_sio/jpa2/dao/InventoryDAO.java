package com.lp3b_sio.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.lp3b_sio.jpa2.model.Inventory;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jpa.Transactional;

public class InventoryDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Inventory searchById(Long ivID) {
		return manager.find(Inventory.class, ivID);
	}
	
	public void save(Inventory inventory) {
		manager.merge(inventory);
	}
	
	@SuppressWarnings("unchecked")
	public List<Inventory> allInventories() {
		return manager.createNamedQuery("Inventory.getAllInventories").getResultList();
	}
	
	@Transactional
	public void delete(Inventory inventory) throws NegocioException {
		inventory = searchById(inventory.getInventoryID());
		
		try {
			manager.remove(inventory);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Inventory is not possible deleted.");
		}
	}
	
	public Long totalInventoriesRegistered() {
		return manager.createQuery("select count(iv) from Inventory iv", Long.class).getSingleResult();
	}
}
