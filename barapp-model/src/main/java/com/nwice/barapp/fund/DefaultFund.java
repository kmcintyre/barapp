package com.nwice.barapp.fund;

import javax.persistence.ManyToOne;

import com.nwice.barapp.model.Cashout;

public class DefaultFund extends GenericFund {
	
	@ManyToOne
	private Cashout cashout;
	
	
	public Cashout getCashout() {
    	return cashout;
    }
    
    public void setCashout(Cashout co) {
    	cashout = co;
    }
	
	public void limitedCopy(DefaultFund dfo) {
		dfo.setTotal(getTotal());
		try {
			dfo.setDescription(getDescription());
		} catch (Exception e) {}
		
	}
}