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
	public void printMenu() {
		System.out.println(this.name+"'s menu:");
		for(Item i : this.menu) {
		System.out.println(i.getName() + ":" + i.getPrice());
		}
		
	}
	public Item getItem(String inpt) {
		for(Item i : this.getMenu()) {
			if(inpt.toLowerCase().compareTo(i.getName().toLowerCase())==0) {
				return i;
			}
		}
		return null;
	}
}
