package com.springboot.pos.system.entity;

import java.text.DecimalFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
    private String name;
	
    @Column(name = "price")
    private float price;

    @Column(name = "qty")
    private float qty;
    
	public Cart() {}

	public Cart(String name, float price, float qty) {
		this.name = name;
		this.price = price;
		this.qty = qty;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}
	
	public String getSub() {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(price*qty);
	}
	
	
	
}
