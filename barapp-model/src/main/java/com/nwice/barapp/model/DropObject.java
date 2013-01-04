package com.nwice.barapp.model;

import com.nwice.barapp.money.ExtendedMoneyObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="tbl_drop")
public class DropObject extends ExtendedMoneyObject {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="drop_id")	
	private Integer dropId;

    public Integer getDropId() {
        return dropId;
    }
    private void setDropId(Integer i) {
    	dropId = i;
    }    
	
}