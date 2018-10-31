package org.mostpates.system;

import java.util.ArrayList;
import java.util.List;

import org.mostpates.checkout.*;
import org.mostpates.people.*;
import org.mostpates.shops.*;

public class System {
	public static List<Customer> customerList;
	public static List<Restaurant> restaurantList;
	public Cashier cashier;
	public System() {
		customerList = new ArrayList<Customer>();
		restaurantList = new ArrayList<Restaurant>();
		cashier = new Cashier();
	}
	public void addRestaurant(Restaurant r) {
		
		return;
	}
	public void addCustomer(Customer c) {
		
		return;
	}
	public Restaurant getRestaurant() {
		
		return null;
	}
	public Customer getCustomer() {
		
		return null;
	}
	public boolean isCustomer() {
		return false;
		
	}
	public boolean isRestaurant() {
		return false;
		
	}

}
