package org.mostpates.shops;

public class Item {
	String name;
	double price;
	public Item() {
		name= "unknown";
		price = 0.00;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return this.price;
	}
}
