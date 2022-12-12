package com.lp3b_sio.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.lp3b_sio.jpa2.dao.CustomerDAO;
import com.lp3b_sio.jpa2.model.Customer;
import com.lp3b_sio.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Customer.class)
public class CustomerConverter implements Converter {

	private CustomerDAO customerDAO;
	
	public CustomerConverter() {
		this.customerDAO = CDIServiceLocator.getBean(CustomerDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Customer cReturn = null;

		if (value != null) {
			cReturn = this.customerDAO.searchById(new Long(value));
		}

		return cReturn;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long cID = ((Customer) value).getCustomerID();
			String cReturn = (cID == null ? null : cID.toString());
			
			return cReturn;
		}
		
		return "";
	}
	
}
