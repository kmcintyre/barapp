package com.nwice.barapp.model;

import com.nwice.barapp.money.DefaultMoneyObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


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
    private void setDrawerId(Integer i) {
    	drawerId = i;
    }    
	
}