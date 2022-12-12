package com.lp3b_sio.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.lp3b_sio.jpa2.model.City;
import com.lp3b_sio.jpa2.service.NegocioException;

public class CityDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public void saveCity(City city) {
		//em.persist(city);
		em.merge(city);
	}
	
	@SuppressWarnings("unchecked")
	public List<City> allCities() {
		return em.createQuery("from City").getResultList();
	}
	
	public void delete(City city) throws NegocioException {
		City cityTemp = em.find(City.class, city.getCityID());
		em.remove(cityTemp);
		em.flush();
	}
	
	public City searchById(Long cID) {
		return em.find(City.class, cID);
	}
}
