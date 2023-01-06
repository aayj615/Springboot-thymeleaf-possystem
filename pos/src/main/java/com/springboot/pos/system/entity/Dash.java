package com.springboot.pos.system.entity;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table
public class Dash {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dateTime", nullable = false)
	private Date dateTime;
	
	@Column(name = "tableno")
	private String tableno;
	
	@Column(name = "itemsname")
	private String itemsname;
	
	@Column(name = "price")
	private String price;
	
	@Column(name = "qty")
	private String qty;
	
	@Column(name = "sub")
	private String sub;
	
	@Column(name = "total")
	private String total;
	
	
	public Dash() {}
	
	public Dash(Date dateTime, String tableno, String itemsname, String price, String qty, String sub, String total) {
		//super();
		this.dateTime = dateTime;
		this.tableno = tableno;
		this.itemsname = itemsname;
		this.price = price;
		this.qty = qty;
		this.sub = sub;
		this.total = total;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	@PrePersist
	private void onCreate() {
	    dateTime = new Date();
	}

	public String getItemsname() {
		return itemsname;
	}

	public void setItemsname(String itemsname) {
		this.itemsname = itemsname;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getTableno() {
		return tableno;
	}

	public void setTableno(String tableno) {
		this.tableno = tableno;
	}

}
