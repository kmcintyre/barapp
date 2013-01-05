package com.nwice.barapp.money;

import javax.persistence.Column;

public class ExtendedMoney extends DefaultMoney {

	@Column(name="hundred")
	private Integer hundred = new Integer(0);
	
	@Column(name="fifty")
    private Integer fifty = new Integer(0);
    
	public Integer getHundred() {
		return hundred;
	}
	public void setHundred(Integer hundred) {
		this.hundred = hundred;
	}
    
    public Integer getFifty() {
		return fifty;
	}
	public void setFifty(Integer fifty) {
		this.fifty = fifty;
	}
	
	public void limitedCopy(ExtendedMoney emo) {
		emo.setHundred(getHundred());
		emo.setFifty(getFifty());
	}
	
}