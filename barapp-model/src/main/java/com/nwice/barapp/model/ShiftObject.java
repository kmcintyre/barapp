package com.nwice.barapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nwice.barapp.DefaultObject;

@Entity
@Table(name="tbl_shift")
public class ShiftObject extends DefaultObject {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shift_id")	
	private Integer shiftId;
	
	@Column(name="shift_date")
	private Date shiftDate;
	
	@Column(name="ampm", length=2)
	private String ampm;
	
	@ManyToOne
	private CashoutObject cashoutObject;
	
	@OneToMany
    private List<ShiftWorkerObject> shiftWorkers = Collections.synchronizedList(new ArrayList<ShiftWorkerObject>());	
	
    public Integer getShiftId() {
        return shiftId;
    }
    public void setShiftId(Integer i) {
    	shiftId = i;
    }
    
    public CashoutObject getCashoutObject() {
    	return cashoutObject;
    }
    
    public void setCashoutObject(CashoutObject co) {
    	cashoutObject = co;
    }
    
    public Date getShiftDate() {
        return shiftDate;
    }
    public void setShiftDate(Date d) {
    	shiftDate = d;
    }
    
    public String getAmpm() {
        return ampm;
    }
    public void setAmpm(String s) {
    	ampm = s;
    }
    
    public List<ShiftWorkerObject> getShiftWorkers() {
        return shiftWorkers;
    }
    
    public void addShiftWorker(ShiftWorkerObject sw) {
    	getShiftWorkers().add(sw);
    }
    
    public void setShiftWorkers(List<ShiftWorkerObject> shiftWorkers) {
        this.shiftWorkers = shiftWorkers;
    }
        	
}