package com.lp3b_sio.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.lp3b_sio.jpa2.dao.SupplierDAO;
import com.lp3b_sio.jpa2.model.Supplier;
import com.lp3b_sio.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Supplier.class)
public class SupplierConverter implements Converter {

	private SupplierDAO supplierDAO;
	
	public SupplierConverter() {
		this.supplierDAO = CDIServiceLocator.getBean(SupplierDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Supplier sReturn = null;

		if (value != null) {
			sReturn = this.supplierDAO.searchById(new Long(value));
		}

		return sReturn;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long sID = ((Supplier) value).getSupplierID();
			String sReturn = (sID == null ? null : sID.toString());
			
			return sReturn;
		}
		
		return "";
	}
	
}
