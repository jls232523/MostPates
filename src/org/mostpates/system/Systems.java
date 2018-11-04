package org.mostpates.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			if (r.getName().toLowerCase().compareTo(name.toLowerCase())==0){
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
				System.out.print(i.getName());
			}
		}		
	}
	public void buildSystem(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File(args[0]));
		Restaurant r;
		Item item;
		String line;
		String[] fields;
		int count =0;
		while(in.hasNextLine()) {
			line = in.nextLine();
			if(count!=0) {
			 fields = line.split(",");
			 r = new Restaurant();
			 r.setName(fields[0]);
			 r.setAddress(fields[1]);
			 this.addRestaurant(r);
			 for(int i=2;i<fields.length;i=i+2) {
				 item = new Item();
				 item.setName(fields[i]);
				 item.setPrice(Double.valueOf(fields[i+1]));
				 r.getMenu().add(item);
			 }
			}
			count+=1;
		}
	}

}
