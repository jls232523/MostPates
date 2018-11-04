package org.mostpates.checkout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mostpates.shops.Item;


public class ShoppingCart {
	List<Item> cart;
	HashMap<Item, Double> price;
	public ShoppingCart() {
		cart = new ArrayList<Item>();
		price = new HashMap<Item,Double>();
	}
	public void addItem(Item i) {
		cart.add(i);
	}
	public List<Item> getItems() {
		return this.cart;
	}
	public String getTotal() {
		double total= 0.0;
		for(Item i : this.getItems()) {
			total = total + i.getPrice();
		}
		return Double.toString(total);
	}
	public void removeItem(Item i) {
		cart.remove(i);
		
	}
	public void printCart() {
		System.out.println("Your Current Cart Has:");
		for(Item i : this.getItems()) {
			System.out.println(i.getName()+" for $" + i.getPrice());
		}
		
	}
}
