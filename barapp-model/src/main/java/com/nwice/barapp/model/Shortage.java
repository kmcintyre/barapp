package com.nwice.barapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.nwice.barapp.fund.DefaultFund;

@Entity
@Table(name="tbl_shortage")
public class Shortage extends DefaultFund {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shortage_id")
	private Integer shortageId;	
	
    public Integer getShortageId() {
        return shortageId;
    }
    public void setShortageId(Integer i) {
    	shortageId = i;
    }
    
}