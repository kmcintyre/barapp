package com.nwice.barapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nwice.barapp.DefaultObject;

@Entity
@Table(name="tbl_shift")
public class Shift extends DefaultObject {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shift_id")	
	private Integer shiftId;
	
	@Column(name="shift_date")
	private Date shiftDate;
	
	@Column(name="ampm", length=2)
	private String ampm;
		
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="shift_id")
    private List<ShiftWorker> shiftWorkers = Collections.synchronizedList(new ArrayList<ShiftWorker>());	
	
    public Integer getShiftId() {
        return shiftId;
    }
    public void setShiftId(Integer i) {
    	shiftId = i;
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
    
    public List<ShiftWorker> getShiftWorkers() {
        return shiftWorkers;
    }
    
    public void addShiftWorker(ShiftWorker sw) {
    	getShiftWorkers().add(sw);
    }
    
    public void setShiftWorkers(List<ShiftWorker> shiftWorkers) {
        this.shiftWorkers = shiftWorkers;
    }
        	
}