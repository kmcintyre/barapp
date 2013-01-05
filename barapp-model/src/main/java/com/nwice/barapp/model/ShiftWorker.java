package com.nwice.barapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.nwice.barapp.DefaultObject;

@Entity
@Table(name="tbl_shift_worker")
public class ShiftWorker extends DefaultObject {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shift_worker_id")
	private Integer shiftWorkerId;
	
	@Column(name="tips")
	private Double tips = new Double(0d);
	@Column(name="payout")
	private Double payout = new Double(0d);
	@Column(name="description")
	private String description;
	
	@Column(name="user")
	private Integer user;

    public Integer getShiftWorkerId() {
        return shiftWorkerId;
    }
    public void setShiftWorkerId(Integer i) {
    	shiftWorkerId = i;
    }
	
    
	public Double getTips() {
		return tips;
	}
	public void setTips(Double tips) {
		this.tips = tips;
	}

	public Double getPayout() {
		return payout;
	}
	public void setPayout(Double d) {
		this.payout = d;
	}
	
    public Integer getUser() {
        return user;
    }

    public void setUser(Integer i) {
        this.user = i;
    }
    
     public String getDescription() {
		return description;
	}
	public void setDescription(String s) {
		this.description = s;
	}
	
}