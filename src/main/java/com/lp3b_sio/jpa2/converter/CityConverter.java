package com.lp3b_sio.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import com.lp3b_sio.jpa2.dao.CityDAO;
import com.lp3b_sio.jpa2.model.City;
import com.lp3b_sio.jpa2.util.cdi.CDIServiceLocator;

//@Named
@FacesConverter(forClass=City.class)
public class CityConverter implements Converter {

	private CityDAO cityDAO;
	
	public CityConverter() {
		this.cityDAO = CDIServiceLocator.getBean( CityDAO.class );
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		City cReturn = null;
		
		if (value != null) {
			cReturn = this.cityDAO.searchById( new Long(value) );
		}
		
		return cReturn;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long cityID = ((City) value).getCityID();
			String cReturn = (cityID == null ? null : cityID.toString());
			
			return cReturn;
		}
		
		return "";
	}

}
