package org.mostpates.shops;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	String name;
	String address;
	List<Item> menu;
	public Restaurant() {
		name = "unknown";
		address = "unknown";
		menu = new ArrayList<Item>();
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Item> getMenu() {
		return this.menu;
	}
	public void setMenu(Item i) {
		this.menu.add(i);
	}
}
