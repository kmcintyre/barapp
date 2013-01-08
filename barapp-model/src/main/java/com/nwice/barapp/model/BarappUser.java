package com.nwice.barapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nwice.barapp.DefaultObject;


@Entity
@Table(name="tbl_bar_user")
public class BarappUser extends DefaultObject {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="barapp_user_id")
    private Integer barappUserId;

	@Column(name="username")
    private String username;
	@Column(name="password")
    private String password;
	@Column(name="firstname")
    private String firstname;
	@Column(name="lastname")
    private String lastname;
	@Column(name="role")
    private String role;
    
	@Column(name="active")
    private Boolean active;

	
    //@JoinTable(name="tbl_bar_user_shift_worker",
    //           joinColumns={@JoinColumn(name="barapp_user_id")},
    //            inverseJoinColumns={@JoinColumn(name="shift_worker_id")})
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "barappUser")
    private Set<ShiftWorker> shiftWorkers = Collections.synchronizedSet(new HashSet<ShiftWorker>());

    public Integer getBarappUserId() {
        return barappUserId;
    }
    public void setBarappUserId(Integer i) {
    	barappUserId = i;
    }    

    public String getUsername() {
        return username;
    }
    public void setUsername(String s) {
    	username = s;
    }
    
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String s) {
    	firstname = s;
    }
	
    public String getRole() {
        return role;
    }
    public void setRole(String s) {
    	role = s;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String s) {
		lastname = s;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String s) {
		password = s;
    }
    
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean b) {
		active = b;
    }
        
    public Set<ShiftWorker> getShiftWorkers() {
        return shiftWorkers;
    }
    
    public void addShiftWorker(ShiftWorker swo) {
    	getShiftWorkers().add(swo);
    }    
    
    void setShiftWorkers(Set<ShiftWorker> shiftWorkers) {
        this.shiftWorkers = shiftWorkers;
    }
    
    public String toString() {
    	return getUsername() + " " + getRole();
    }

}
