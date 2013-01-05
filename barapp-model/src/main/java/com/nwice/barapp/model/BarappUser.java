package com.nwice.barapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nwice.barapp.DefaultObject;


@Entity
@Table(name="tbl_bar_user")
public class BarappUser extends DefaultObject {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
    private Integer userId;

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
    
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<ShiftWorker> shiftWorkers = Collections.synchronizedList(new ArrayList<ShiftWorker>());

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer i) {
    	userId = i;
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
        
    public List<ShiftWorker> getShiftWorkers() {
        return shiftWorkers;
    }
    
    public void addShiftWorker(ShiftWorker swo) {
    	getShiftWorkers().add(swo);
    }    
    
    void setShiftWorkers(List<ShiftWorker> shiftWorkers) {
        this.shiftWorkers = shiftWorkers;
    }
    
    public String toString() {
    	return getUsername() + " " + getRole();
    }

}
