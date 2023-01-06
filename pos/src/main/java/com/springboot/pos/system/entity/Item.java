package com.springboot.pos.system.entity;

/**
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
**/

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Item {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@NotEmpty(message = "* Name is required")
	@Column(name = "name", unique = true)
    //@NotBlank
    private String name;
    
	@NotEmpty(message = "* Description is required")
    //@NotBlank
    //@Pattern(regexp=".+@.+\\.[a-z]+")
    @Column(name = "desc")
    private String desc;
    
	@NotEmpty(message = "* Category is required")
    //@NotBlank
    @Column(name = "category")
    private String category;
    
	@NotNull(message = "* Price is required")
    //@NotBlank
    @Column(name = "price")
    private float price;
	
    public Item() {
    	
    }

    public Item(String name, String desc, String category, float price ) {
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.price = price;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public void setCategory(String category) {
		this.category = category;
	}
    
    public void setPrice(float price) {
		this.price = price;
	}

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

	public String getCategory() {
		return category;
	}
	
	public float getPrice() {
		return price;
	}

	
}
