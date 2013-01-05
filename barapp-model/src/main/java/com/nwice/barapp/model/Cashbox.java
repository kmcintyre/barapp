package com.nwice.barapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.nwice.barapp.money.ExtendedMoney;

@Entity
@Table(name="tbl_cashbox")
public class Cashbox extends ExtendedMoney {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cashbox_id")	
	private Integer cashboxId;
	
    public Integer getCashboxId() {
        return cashboxId;
    }    
	public void setCashboxId(Integer i) {
    	cashboxId = i;
    }    
	
}