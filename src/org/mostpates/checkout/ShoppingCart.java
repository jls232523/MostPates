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
}
