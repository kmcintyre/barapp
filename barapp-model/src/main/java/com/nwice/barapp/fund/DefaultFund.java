package com.nwice.barapp.fund;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.nwice.barapp.DefaultObject;

@MappedSuperclass
public class DefaultFund extends DefaultObject {

	@Column(name="total")
	private Double total = new Double( Double.parseDouble("0.0") );
	
	@Column(name="description", length=128)
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
	
	public void limitedCopy(DefaultFund dfo) {
		dfo.setTotal(getTotal());
		try {
			dfo.setDescription(getDescription());
		} catch (Exception e) {}
		
	}
	
}