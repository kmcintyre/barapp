package com.nwice.barapp.model;

import com.nwice.barapp.money.DefaultMoneyObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="tbl_start_drawer")
public class StartDrawerObject extends DefaultMoneyObject {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="start_drawer_id")	
	private Integer startDrawerId;
	
    public Integer getStartDrawerId() {
        return startDrawerId;
    }
    private void setStartDrawerId(Integer i) {
    	startDrawerId = i;
    }    
	
}