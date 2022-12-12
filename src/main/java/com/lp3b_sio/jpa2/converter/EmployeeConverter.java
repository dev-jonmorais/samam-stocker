package com.lp3b_sio.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.lp3b_sio.jpa2.dao.CustomerDAO;
import com.lp3b_sio.jpa2.dao.EmployeeDAO;
import com.lp3b_sio.jpa2.model.Customer;
import com.lp3b_sio.jpa2.model.Employee;
import com.lp3b_sio.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Employee.class)
public class EmployeeConverter implements Converter {

	private EmployeeDAO employeeDAO;
	
	public EmployeeConverter() {
		this.employeeDAO = CDIServiceLocator.getBean(EmployeeDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Employee eReturn = null;

		if (value != null) {
			eReturn = this.employeeDAO.searchById(new Long(value));
		}

		return eReturn;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long eID = ((Employee) value).getEmployeeID();
			String eReturn = (eID == null ? null : eID.toString());
			
			return eReturn;
		}
		
		return "";
	}
	
}
