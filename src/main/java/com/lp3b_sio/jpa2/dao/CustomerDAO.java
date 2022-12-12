package com.lp3b_sio.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.lp3b_sio.jpa2.model.Customer;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jpa.Transactional;

public class CustomerDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public void saveCustomer(Customer customer) {
		//em.persist(customer);
		em.merge(customer);
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> allCustomers() {
		return em.createQuery("from Customer").getResultList();
	}

	@Transactional
	public void delete(Customer customer) throws NegocioException {
		Customer customerTemp = em.find(Customer.class, customer.getCustomerID());
		em.remove(customerTemp);
		em.flush();
	}
	
	public Customer searchById(Long cID) {
		return em.find(Customer.class, cID);
	}
}
