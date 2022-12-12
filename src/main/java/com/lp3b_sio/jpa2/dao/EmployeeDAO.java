package com.lp3b_sio.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.lp3b_sio.jpa2.model.Employee;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jpa.Transactional;

public class EmployeeDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public void saveEmployee(Employee employee) {
		em.merge(employee);
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> allEmployees() {
		return em.createQuery("from Employee").getResultList();
	}

	@Transactional
	public void delete(Employee employee) throws NegocioException {
		Employee employeeTemp = em.find(Employee.class, employee.getEmployeeID());
		em.remove(employeeTemp);
		em.flush();
	}
	
	public Employee searchById(Long eID) {
		return em.find(Employee.class, eID);
	}
}
