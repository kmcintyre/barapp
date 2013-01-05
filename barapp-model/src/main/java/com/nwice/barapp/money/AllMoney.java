package com.nwice.barapp.money;

import javax.persistence.Column;

public class AllMoney extends ExtendedMoney {

	@Column(name="quarter")
    private Integer quarter = new Integer(0);
	
	@Column(name="dime")
    private Integer dime = new Integer(0);
	
	@Column(name="nickel")
    private Integer nickel = new Integer(0);
	
	@Column(name="penny")
    private Integer penny = new Integer(0);
    
	public Integer getQuarter() {
		return quarter;
	}
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}
	
	public Integer getDime() {
		return dime;
	}
	public void setDime(Integer dime) {
		this.dime = dime;
	}
    
	public Integer getNickel() {
		return nickel;
	}
	public void setNickel(Integer nickel) {
		this.nickel = nickel;
	}
	
	public Integer getPenny() {
		return penny;
	}
	public void setPenny(Integer penny) {
		this.penny = penny;
	}
	
}