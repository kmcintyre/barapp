package com.nwice.barapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.nwice.barapp.fund.GenericFund;

@Entity
@Table(name="tbl_payout")
public class Payout extends GenericFund {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="payout_id")	
	private Integer payoutId;

	@Column(name="name")
	private String name;
	
    public Integer getPayoutId() {
        return payoutId;
    }
    public void setPayoutId(Integer i) {
    	payoutId = i;
    }
    
	public String getName() {
		return name;
	}
	public void setName(String s) {
		this.name = s;
	}
    
}