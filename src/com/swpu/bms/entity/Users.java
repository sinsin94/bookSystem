package com.swpu.bms.entity;

import com.google.gson.annotations.SerializedName;
import com.swpu.bms.annotation.Column;
import com.swpu.bms.annotation.ID;
import com.swpu.bms.annotation.Table;

@Table(name="users")
public class Users {
	
	@ID(name="id",isAutoIncrement=true)
	@SerializedName("id")
	private int id;
	
	@Column(name="userName")
	@SerializedName("username")
	private String username;
	
	@Column(name="password")
	@SerializedName("password")
	private String password;
	
	@Column(name="realName")
	@SerializedName("realname")
	private String realname;
	
	
	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Users(int id, String username, String password, String realname) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
	}


	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", realname=" + realname + "]";
	}
	
	
}
