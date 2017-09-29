package com.swpu.bms.entity;

import com.google.gson.annotations.SerializedName;
import com.swpu.bms.annotation.Column;
import com.swpu.bms.annotation.ID;
import com.swpu.bms.annotation.Table;

@Table(name="publishers")
public class Publishers {
	
	@ID(name="id",isAutoIncrement=true)
	@SerializedName("id")
	private int id;
	
	
	@Column(name="name")
	@SerializedName("name")
	private String name;
	public Publishers() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Publishers(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
