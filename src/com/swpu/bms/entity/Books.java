package com.swpu.bms.entity;

import com.google.gson.annotations.SerializedName;
import com.swpu.bms.annotation.Column;
import com.swpu.bms.annotation.ID;
import com.swpu.bms.annotation.Table;

@Table(name="books")
public class Books {

	@ID(name="isbn",isAutoIncrement=false)
	@SerializedName("isbn")
	private String isbn;
	
	@Column(name="name")
	@SerializedName("name")
	private String name;
	
	@Column(name="version")
	@SerializedName("version")
	private int version;
	
	@Column(name="price")
	@SerializedName("price")
	private float price;
	
	@Column(name="publishTime")
	@SerializedName("publishTime")
	private String publishTime;
	
	@Column(name="publisherId")
	@SerializedName("publisherId")
	private int publisherId;
	
	
	//非持久化数据
	private String publishername;
	
	public Books() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Books(String isbn, String name, int version, float price, String publishTime, int publisherId) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.version = version;
		this.price = price;
		this.publishTime = publishTime;
		this.publisherId = publisherId;
	}


	


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getPublishTime() {
		return publishTime;
	}


	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}


	public int getPublisherId() {
		return publisherId;
	}


	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}


	public String getPublishername() {
		return publishername;
	}


	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}


	@Override
	public String toString() {
		return "Books [isbn=" + isbn + ", name=" + name + ", version=" + version + ", price=" + price + ", publishTime="
				+ publishTime + ", publisherId=" + publisherId + "]";
	}
	
	
}
