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
			if (r.getName().compareTo(name)==0){
				return r;
			}
		}
		return null;
	}
	public Customer getCustomer(String name) {
		for(Customer c : customerList) {
			if (c.getName().compareTo(name)==0){
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

}
