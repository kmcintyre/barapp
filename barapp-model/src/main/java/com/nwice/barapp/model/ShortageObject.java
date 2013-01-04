package com.nwice.barapp.model;

import java.util.Set;

import com.nwice.barapp.money.DefaultFundObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="tbl_shortage")
public class ShortageObject extends DefaultFundObject {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shortage_id")
	private Integer shortageId;	
	
    public Integer getShortageId() {
        return shortageId;
    }
    private void setShortageId(Integer i) {
    	shortageId = i;
    }
    
}