package com.nwice.barapp.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Iterator;

import com.nwice.barapp.DefaultObject;
import com.nwice.barapp.money.DefaultFundObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Entity
@Table(name="tbl_cashout")
@NamedQueries({
	@NamedQuery(name="dateSearch", query="from CashoutObject co where co.shiftObject.shiftDate >= :fromDate and co.shiftObject.shiftDate <= :toDate order by co.shiftObject.shiftDate desc"),
	@NamedQuery(name="exactShift", query="from CashoutObject co where co.shiftObject.shiftDate = :shiftDate"),
	@NamedQuery(name="oneBeforeSearch", query="from CashoutObject co where co.shiftObject.shiftDate < :beforeDate order by co.shiftObject.shiftDate desc")
})
public class CashoutObject extends DefaultObject {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cashout_id")	
	private Integer cashoutId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DrawerObject drawerObject;

	@OneToOne(cascade = CascadeType.ALL)
	private StartDrawerObject startDrawerObject;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CashboxObject cashboxObject;
	
	@OneToOne(cascade = CascadeType.ALL)
	private StartCashboxObject startCashboxObject;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ShiftObject shiftObject;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DropObject dropObject;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set payouts = Collections.synchronizedSet(new HashSet());
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set shortages = Collections.synchronizedSet(new HashSet());
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set overrings = Collections.synchronizedSet(new HashSet());
	

	public Integer getCashoutId() {
        return cashoutId;
    }
    public void setCashoutId(Integer i) {
    	cashoutId = i;
    }
        
    public ShiftObject getShiftObject() {
        return shiftObject;
    }    
    public void setShiftObject(ShiftObject s) {
        shiftObject = s;
    }
    
    public DrawerObject getDrawerObject() {
        return drawerObject;
    }    
    public void setDrawerObject(DrawerObject d) {
        drawerObject = d;
    }
    
    public StartDrawerObject getStartDrawerObject() {
        return startDrawerObject;
    }    
    public void setStartDrawerObject(StartDrawerObject d) {
        startDrawerObject = d;
    }
    
    public CashboxObject getCashboxObject() {
        return cashboxObject;
    }    
    public void setCashboxObject(CashboxObject c) {
        cashboxObject = c;
    }
    
    public StartCashboxObject getStartCashboxObject() {
        return startCashboxObject;
    }    
    public void setStartCashboxObject(StartCashboxObject c) {
        startCashboxObject = c;
    }
    
    public DropObject getDropObject() {
        return dropObject;
    }    
    public void setDropObject(DropObject d) {
    	dropObject = d;
    }
                    
    public Set getPayouts() {
        return payouts;
    }
            
    public void addPayout(PayoutObject po) {
    	getPayouts().add(po);
    }
    
    public void setPayouts(Set payouts) {
        this.payouts = payouts;
    }
    
    public Set getShortages() {
        return shortages;
    }
    
    public void addShortage(ShortageObject so) {
    	getShortages().add(so);
    }
    
    public void setShortages(Set shortages) {
        this.shortages = shortages;
    }
    
    public Set getOverrings() {
        return overrings;
    }
    
    public void addOverring(OverringObject so) {
    	getOverrings().add(so);
    }
    
    public void setOverrings(Set overrings) {
        this.overrings = overrings;
    }
	
}