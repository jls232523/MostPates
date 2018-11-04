package org.mostpates.system;

import java.util.ArrayList;
import java.util.List;

import org.mostpates.checkout.*;
import org.mostpates.people.*;
import org.mostpates.shops.*;

public class Systems {
	public static List<Customer> customerList;
	public static List<Restaurant> restaurantList;
	public Cashier cashier;
	public Systems() {
		customerList = new ArrayList<Customer>();
		restaurantList = new ArrayList<Restaurant>();
		cashier = new Cashier();
	}
	public void addRestaurant(Restaurant r) {
		restaurantList.add(r);
		return;
	}
	public void addCustomer(Customer c) {
		customerList.add(c);
		return;
	}
	public Restaurant getRestaurant(String name) {
		for(Restaurant r : restaurantList) {
			if (r.getName().toLowerCase().compareTo(name)==0){
				return r;
			}
		}
		return null;
	}
	public Customer getCustomer(String name) {
		for(Customer c : customerList) {
			if (c.getName().toLowerCase().compareTo(name)==0){
				return c;
			}
		}
		return null;
	}
	public boolean isCustomer() {
		return false;
		
	}
	public boolean isRestaurant() {
		return false;
		
	}
	public void printRestaurants() {
		for(Restaurant r : restaurantList) {
			System.out.println(r.getName());
		}
		
	}
	public void printCustomers() {
		for(Customer c : customerList) {
			System.out.println(c.getName());
		}
	}
	public void printItems() {
		for(Restaurant r : restaurantList) {
			for(Item i :r.getMenu()) {
				System.out.println(i.getName());
			}
		}		
	}

}
