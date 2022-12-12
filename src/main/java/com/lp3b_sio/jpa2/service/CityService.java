package com.lp3b_sio.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.lp3b_sio.jpa2.dao.CityDAO;
import com.lp3b_sio.jpa2.model.City;
import com.lp3b_sio.jpa2.util.jpa.Transactional;

public class CityService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CityDAO cityDAO;
	
	@Transactional
	public void save(City city) throws NegocioException {
		if ( city.getCityName() == null || city.getCityName().trim().equals("") ) { 
			throw new NegocioException("The CITY NAME cannot be empty!");
		}
		this.cityDAO.saveCity(city);
	}

}
