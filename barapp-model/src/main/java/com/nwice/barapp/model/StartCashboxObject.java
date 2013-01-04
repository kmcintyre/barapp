package com.nwice.barapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.nwice.barapp.money.ExtendedMoneyObject;

@Entity
@Table(name="tbl_start_cashbox")
public class StartCashboxObject extends ExtendedMoneyObject {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="start_cashbox_id")	
	private Integer startCashboxId;
	
    public Integer getStartCashboxId() {
        return startCashboxId;
    }
    public void setStartCashboxId(Integer i) {
    	startCashboxId = i;
    }    
	
}