package com.lp3b_sio.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.lp3b_sio.jpa2.model.State;

public class StateDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<State> allStates() {
		return em.createQuery("from State").getResultList();
	}
	
	public State searchById(Long sID) {
		return em.find(State.class, sID);
	}

}
