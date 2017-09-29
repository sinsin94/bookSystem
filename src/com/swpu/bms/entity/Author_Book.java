package com.swpu.bms.entity;

import com.google.gson.annotations.SerializedName;
import com.swpu.bms.annotation.Column;
import com.swpu.bms.annotation.Table;

@Table(name="author_book")
public class Author_Book {

	@Column(name="authorID")
	@SerializedName("authorID") //gson 数据解析时 字符串中的key 与属性名不一样的处理方法
	private int authorID;
	
	@Column(name="isbn")
	@SerializedName("isbn")
	private String isbn;
	
	
	public Author_Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Author_Book(int authorID, String isbn) {
		super();
		this.authorID = authorID;
		this.isbn = isbn;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
	
	
}
