package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

//import com.lp3b_sio.jpa2.dao.CityDAO;
import com.lp3b_sio.jpa2.dao.StateDAO;
import com.lp3b_sio.jpa2.model.City;
import com.lp3b_sio.jpa2.model.State;
import com.lp3b_sio.jpa2.service.CityService;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CreateCityBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<State> states;
	private City city;
	
	@Inject
	private CityService cityService;
	
	@Inject
	private StateDAO stateDAO;
	
	/**
	 * SAVE CITY
	 */
	public void save() {
		try {
			this.cityService.save(city);
			FacesUtil.addSuccessMessage("City saved successfully!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.clear();
	}
	
	/**
	 * INIT CITY
	 */
	@PostConstruct
	public void init() {
		this.clear();
		this.states = this.stateDAO.allStates();
	}
	
	/**
	 * CLEAR CITY
	 */
	public void clear() {
		this.city = new City();
	}
	
	/**
	 * GET CITY
	 */
	public City getCity() {
		return city;
	}
	
	/**
	 * SET CITY
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * GET STATES
	 */
	public List<State> getStates() {
		return states;
	}

}
