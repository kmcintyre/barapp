package com.nwice.barapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.nwice.barapp.money.DefaultMoneyObject;


@Entity
@Table(name="tbl_drawer")
public class DrawerObject extends DefaultMoneyObject {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="drawer_id")	
	private Integer drawerId;
	
    public Integer getDrawerId() {
        return drawerId;
    }
    public void setDrawerId(Integer i) {
    	drawerId = i;
    }    
	
}