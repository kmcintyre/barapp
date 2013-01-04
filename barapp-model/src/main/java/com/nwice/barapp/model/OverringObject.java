package com.nwice.barapp.model;

import com.nwice.barapp.money.DefaultFundObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


@Entity
@Table(name="tbl_overring")
public class OverringObject extends DefaultFundObject {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="overring_id")	
	private Integer overringId;	
	
    public Integer getOverringId() {
        return overringId;
    }
    private void setOverringId(Integer i) {
    	overringId = i;
    }
    
}