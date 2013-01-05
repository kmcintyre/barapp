package com.nwice.barapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.nwice.barapp.money.ExtendedMoney;

@Entity
@Table(name="tbl_drop")
public class Drop extends ExtendedMoney {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="drop_id")	
	private Integer dropId;

    public Integer getDropId() {
        return dropId;
    }
    public void setDropId(Integer i) {
    	dropId = i;
    }    
	
}