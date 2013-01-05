package com.nwice.barapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.nwice.barapp.fund.DefaultFund;


@Entity
@Table(name="tbl_overring")
public class Overring extends DefaultFund {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="overring_id")	
	private Integer overringId;	
	
    public Integer getOverringId() {
        return overringId;
    }
    public void setOverringId(Integer i) {
    	overringId = i;
    }
    
}