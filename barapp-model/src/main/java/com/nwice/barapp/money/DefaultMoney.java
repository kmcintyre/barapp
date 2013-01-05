package com.nwice.barapp.money;

import javax.persistence.Column;

import com.nwice.barapp.fund.DefaultFund;

public class DefaultMoney extends DefaultFund {

	@Column(name="twenty")
	private Integer twenty = new Integer(0);
	
	@Column(name="ten")
    private Integer ten = new Integer(0);
	
	@Column(name="five")
    private Integer five = new Integer(0);
	
	@Column(name="single")
    private Integer single = new Integer(0);
        
	public Integer getSingle() {
		return single;
	}
	public void setSingle(Integer single) {
		this.single = single;
	}
    
	public Integer getFive() {
		return five;
	}
	public void setFive(Integer five) {
		this.five = five;
	}

	public Integer getTen() {
		return ten;
	}
	public void setTen(Integer ten) {
		this.ten = ten;
	}

	public Integer getTwenty() {
		return twenty;
	}
	public void setTwenty(Integer twenty) {
		this.twenty = twenty;
	}    
	
	public void limitedCopy(DefaultMoney dmo) {
		dmo.setTwenty(getTwenty());
		dmo.setTen(getTen());
		dmo.setFive(getFive());
		dmo.setSingle(getSingle());
	}
}