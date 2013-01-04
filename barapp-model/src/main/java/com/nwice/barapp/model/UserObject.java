package com.nwice.barapp.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.nwice.barapp.DefaultObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;


@Entity
@Table(name="tbl_user")
public class UserObject extends DefaultObject {

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
    private List shiftWorkers = Collections.synchronizedList(new ArrayList());

    public Integer getUserId() {
        return userId;
    }
    private void setUserId(Integer i) {
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
        
    public List getShiftWorkers() {
        return shiftWorkers;
    }
    
    public void addShiftWorker(ShiftWorkerObject swo) {
    	getShiftWorkers().add(swo);
    }    
    
    void setShiftWorkers(List shiftWorkers) {
        this.shiftWorkers = shiftWorkers;
    }
    
    public String toString() {
    	return getUsername() + " " + getRole();
    }

}
