package com.swpu.bms.entity;

import com.google.gson.annotations.SerializedName;
import com.swpu.bms.annotation.Column;
import com.swpu.bms.annotation.ID;
import com.swpu.bms.annotation.Table;

@Table(name="authors")
public class Authors {

	@ID(name="id",isAutoIncrement=true)
	@SerializedName("id")
	private int id;
	
	@Column(name="firstName")
	@SerializedName("firstname")
	private String firstname;
	
	
	@Column(name="lastName")
	@SerializedName("lastname")
	private String lastname;
	
	public Authors() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Authors(int id, String firstname, String lastname) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	@Override
	public String toString() {
		return "Authors [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
	
	
}
