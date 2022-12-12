package com.lp3b_sio.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.lp3b_sio.jpa2.dao.InventoryDAO;
import com.lp3b_sio.jpa2.model.Inventory;
import com.lp3b_sio.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Inventory.class)
public class InventoryConverter implements Converter {

	private InventoryDAO inventoryDAO;
	
	public InventoryConverter() {
		this.inventoryDAO = CDIServiceLocator.getBean(InventoryDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Inventory ivReturn = null;
		
		if (value != null) {
			ivReturn = this.inventoryDAO.searchById(new Long(value));
		}

		return ivReturn;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long ivID = ((Inventory) value).getInventoryID();
			String ivReturn = (ivID == null ? null : ivID.toString());
			
			return ivReturn;
		}
		
		return "";
	}
}
