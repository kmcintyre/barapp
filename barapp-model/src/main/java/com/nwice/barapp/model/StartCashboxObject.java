package com.nwice.barapp.model;

import java.util.Set;

import com.nwice.barapp.money.ExtendedMoneyObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="tbl_start_cashbox")
public class StartCashboxObject extends ExtendedMoneyObject {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="start_cashbox_id")	
	private Integer startCashboxId;
	
    /**
	* @hibernate.id
	*  generator-class="native"
	*  column="start_cashbox_id"
	*/    
    
    public Integer getStartCashboxId() {
        return startCashboxId;
    }
    private void setStartCashboxId(Integer i) {
    	startCashboxId = i;
    }    
	
}