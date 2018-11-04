package org.mostpates.people;

import org.mostpates.checkout.*;
import org.mostpates.shops.Item;

public class Customer {
	public String name;
	public String phone;
	public String address;
	public ShoppingCart cart;
	public Coupon coupon;
	
	public Customer() {
		name ="unknown";
		phone ="0";
		address ="unknown";
		cart = new ShoppingCart();
		coupon = new Coupon();
	}
	public void setName(String name) {
		this.name  = name;
	}
	public String getName() {
		return this.name;
	}
	public void setAddress(String add) {
		this.address  = add;
	}
	public String getAddress() {
		return this.address;
	}
	public void setPhone(String userIn) {
		this.phone  = userIn;
	}
	public String getPhone() {
		return this.phone;
	}
	public int getCode() {
		return 0;
		//TODO FIXME
	}
	public void setCode(int code) {
		//TODO FIXME
	}
	public void addToCart(Item newItem) {
		this.getCart().addItem(newItem);
	}
	public ShoppingCart getCart() {
		return cart;
	}
	public void order() {
		// TODO Auto-generated method stub
		
	}
	public void removeFromCart(Item newItem) {
		this.getCart().removeItem(newItem);
		
	}
}
