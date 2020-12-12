package com.duolingo.client.rmi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Serializable {

	private long product_id;
	private String product_name;
	private double product_price;
	private boolean isPurchased;

	private List<Shop> products;
	
	// Constructors
	public Shop() {
		this.products = new ArrayList<Shop>();
	}
	
	public Shop(String product_name, double product_price) {
		super();
		this.product_name = product_name;
		this.product_price = product_price;
		this.products = new ArrayList<Shop>();
	}

	public Shop(long product_id, String product_name, double product_price) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.products = new ArrayList<Shop>();
	}

	// Getters && Setters:
	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public boolean isPurchased() {
		return isPurchased;
	}

	public void setPurchased(boolean isPurchased) {
		this.isPurchased = isPurchased;
	}

	public List<Shop> getProducts() {
		return products;
	}

	public void setProducts(List<Shop> products) {
		this.products = products;
	}
	
	public void addProduct(Shop product) {
		this.products.add(product);
	}
}
