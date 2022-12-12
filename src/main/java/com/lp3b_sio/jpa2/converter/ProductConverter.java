package com.lp3b_sio.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.lp3b_sio.jpa2.dao.ProductDAO;
import com.lp3b_sio.jpa2.model.Product;
import com.lp3b_sio.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Product.class)
public class ProductConverter implements Converter {

	private ProductDAO productDAO;
	
	public ProductConverter() {
		this.productDAO = CDIServiceLocator.getBean(ProductDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Product pReturn = null;

		if (value != null) {
			pReturn = this.productDAO.searchById(new Long(value));
		}

		return pReturn;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long pID = ((Product) value).getProductID();
			String pReturn = (pID == null ? null : pID.toString());
			
			return pReturn;
		}
		
		return "";
	}
}
