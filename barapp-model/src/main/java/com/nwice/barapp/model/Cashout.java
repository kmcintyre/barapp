package com.nwice.barapp.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.nwice.barapp.DefaultObject;

@Entity
@Table(name="tbl_cashout")
@NamedQueries({
	@NamedQuery(name="dateSearch", query="from Cashout co where co.shift.shiftDate >= :fromDate and co.shift.shiftDate <= :toDate order by co.shift.shiftDate desc"),
	@NamedQuery(name="exactShift", query="from Cashout co where co.shift.shiftDate = :shiftDate"),
	@NamedQuery(name="oneBeforeSearch", query="from Cashout co where co.shift.shiftDate < :beforeDate order by co.shift.shiftDate desc")
})
public class Cashout extends DefaultObject {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cashout_id")	
	private Integer cashoutId;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="drawer_id")
	private Drawer drawer;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="start_drawer_id")
	private Drawer startDrawer;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="cashbox_id")
	private Cashbox cashbox;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="start_cashbox_id")
	private Cashbox startCashbox;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="shift_id")
	private Shift shift;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="drop_id")
	private Drop drop;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="cashout_id")
	private Set<Payout> payouts = Collections.synchronizedSet(new HashSet<Payout>());
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="cashout_id")
	private Set<Shortage> shortages = Collections.synchronizedSet(new HashSet<Shortage>());
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="cashout_id")
	private Set<Overring> overrings = Collections.synchronizedSet(new HashSet<Overring>());
	

	public Integer getCashoutId() {
        return cashoutId;
    }
    public void setCashoutId(Integer i) {
    	cashoutId = i;
    }
        
    public Shift getShift() {
        return shift;
    }    
    public void setShift(Shift s) {
        shift = s;
    }
    
    public Drawer getDrawer() {
        return drawer;
    }    
    public void setDrawer(Drawer d) {
        drawer = d;
    }
    
    public Drawer getStartDrawer() {
        return startDrawer;
    }    
    public void setStartDrawer(Drawer d) {
        startDrawer = d;
    }
    
    public Cashbox getCashbox() {
        return cashbox;
    }    
    public void setCashbox(Cashbox c) {
        cashbox = c;
    }
    
    public Cashbox getStartCashbox() {
        return startCashbox;
    }    
    public void setStartCashbox(Cashbox c) {
        startCashbox = c;
    }
    
    public Drop getDrop() {
        return drop;
    }    
    public void setDrop(Drop d) {
    	drop = d;
    }
                    
    public Set<Payout> getPayouts() {
        return payouts;
    }
            
    public void addPayout(Payout po) {
    	getPayouts().add(po);
    }
    
    public void setPayouts(Set<Payout> payouts) {
        this.payouts = payouts;
    }
    
    public Set<Shortage> getShortages() {
        return shortages;
    }
    
    public void addShortage(Shortage so) {
    	getShortages().add(so);
    }
    
    public void setShortages(Set<Shortage> shortages) {
        this.shortages = shortages;
    }
    
    public Set<Overring> getOverrings() {
        return overrings;
    }
    
    public void addOverring(Overring so) {
    	getOverrings().add(so);
    }
    
    public void setOverrings(Set<Overring> overrings) {
        this.overrings = overrings;
    }
	
}