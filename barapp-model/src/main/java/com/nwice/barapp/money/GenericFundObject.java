package com.nwice.barapp.money;

import javax.persistence.Column;

import com.nwice.barapp.DefaultObject;

public class GenericFundObject extends DefaultObject {

	@Column(name="total")
	private Double total = new Double( Double.parseDouble("0.0") );
	
	@Column(name="description")
	private String description;
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String s) {
		this.description = s;
	}
	
}