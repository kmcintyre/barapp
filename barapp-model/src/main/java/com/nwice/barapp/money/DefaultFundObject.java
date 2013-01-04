package com.nwice.barapp.money;

import javax.persistence.ManyToOne;

import com.nwice.barapp.model.CashoutObject;

public class DefaultFundObject extends GenericFundObject {
	
	@ManyToOne
	private CashoutObject cashoutObject;
	
	
	public CashoutObject getCashoutObject() {
    	return cashoutObject;
    }
    
    public void setCashoutObject(CashoutObject co) {
    	cashoutObject = co;
    }
	
	public void limitedCopy(DefaultFundObject dfo) {
		dfo.setTotal(getTotal());
		try {
			dfo.setDescription(getDescription());
		} catch (Exception e) {}
		
	}
}