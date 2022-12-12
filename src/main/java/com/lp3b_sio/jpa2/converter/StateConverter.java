package com.lp3b_sio.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.lp3b_sio.jpa2.dao.StateDAO;
import com.lp3b_sio.jpa2.model.State;
import com.lp3b_sio.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=State.class)
public class StateConverter implements Converter {

	private StateDAO stateDAO;
	
	public StateConverter() {
		this.stateDAO = CDIServiceLocator.getBean( StateDAO.class );
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		State sReturn = null;
		
		if (value != null) {
			sReturn = this.stateDAO.searchById( new Long(value) );
		}
		
		return sReturn;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long stateID = ((State) value).getStateID();
			String sReturn = (stateID == null ? null : stateID.toString());
			
			return sReturn;
		}
		
		return "";
	}

}
