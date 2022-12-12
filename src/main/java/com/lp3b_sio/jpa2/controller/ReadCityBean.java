package com.lp3b_sio.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lp3b_sio.jpa2.dao.CityDAO;
import com.lp3b_sio.jpa2.model.City;
import com.lp3b_sio.jpa2.service.NegocioException;
import com.lp3b_sio.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ReadCityBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	CityDAO cityDAO;
	
	private List<City> cities = new ArrayList<>();
	
	/**
	 * GET CITIES
	 */
	public List<City> getCities() {
		return cities;
	}
	
	private City citySelected;
	
	/**
	 * GET CITY SELECTED
	 */
	public City getCitySelected() {
		return citySelected;
	}
	
	/**
	 * SET CITY SELECTED
	 */
	public void setCitySelected(City citySelected) {
		this.citySelected = citySelected;
	}
	
	/**
	 * DELETE CITY SELECTED
	 */
	public void delete() {
		try {
			cityDAO.delete(citySelected);
			this.cities.remove(citySelected);
			FacesUtil.addSuccessMessage("City Name " + citySelected.getCityName() + " deleted successfully.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**
	 * INIT CITY
	 */
	@PostConstruct
	public void init() {
		cities = cityDAO.allCities();
	}
}
