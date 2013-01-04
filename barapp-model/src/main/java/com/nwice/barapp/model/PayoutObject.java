package com.nwice.barapp.model;

import com.nwice.barapp.money.DefaultFundObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="tbl_payout")
public class PayoutObject extends DefaultFundObject {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="payout_id")	
	private Integer payoutId;

	private String name;
	
    public Integer getPayoutId() {
        return payoutId;
    }
    private void setPayoutId(Integer i) {
    	payoutId = i;
    }
    
	/**
     * @hibernate.property
     *  column="name"
     */    		
	public String getName() {
		return name;
	}
	public void setName(String s) {
		this.name = s;
	}
    
}