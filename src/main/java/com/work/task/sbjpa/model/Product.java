package com.work.task.sbjpa.model;

import java.math.BigInteger;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	@Column(name="category")
	private String category;
	@Column(name="price")
	private String price;
	@Column(name="stocked")
	private String stocked;
	@Column(name="pname")
	private String pname;
	


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStocked() {
		return stocked;
	}
	public void setStocked(String stocked) {
		this.stocked = stocked;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
}
