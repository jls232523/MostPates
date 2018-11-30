package org.mostpates.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
	public void buildSystem() throws FileNotFoundException {
		File currentDir = new File("");
		String path = (currentDir.getAbsolutePath() + "/src/InputFiles/RestaurantItemList");
		Scanner in = new Scanner(new File(path));
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
			 r.setImage(fields[2]);
			 this.addRestaurant(r);
			 for(int i=3;i<fields.length;i=i+2) {
				 item = new Item();
				 item.setName(fields[i]);
				 item.setPrice(Double.valueOf(fields[i+1]));
				 r.getMenu().add(item);
			 }
			}
			count+=1;
		}
	}
	public void getDistances(Customer c1) throws IOException {
		/*String str="",str2 = "";
		URL direct;	
		URLConnection direcConnect;
		BufferedReader in2 = null ;
		try {
		for(Restaurant r : this.restaurantList) {
			direct = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+r.getAddress().replaceAll(" ", "")+"&destinations="+c1.getAddress().replaceAll(" ", "")+"&key=AIzaSyBl49PQg0nL_4KAEjWXMB1hFT0xqjdTjco
");
			direcConnect = direct.openConnection();//opens a connection to GoogleMaps to get distance and time estimates
			in2 = new BufferedReader(new InputStreamReader(direcConnect.getInputStream()));
			String inputLine;
			direcConnect.connect();
			
			int check = 0;
			while ((inputLine = in2.readLine()) != null) {
				if(inputLine.contains("mins")) {
				str+=inputLine;
				}
				if(inputLine.contains("distance")||check==1) {
					str2+=inputLine;
					check+=1;
				}
			}
			in2.close();
			String[] s = str.split(":");//parse file given back by GoogleMaps
			String s1= s[1].split(" ")[1];
			s1 = s1.replaceAll("\"", "");
			double num = Double.valueOf(s1);
			s = str2.split(":");
			s1 = s[2].split(" ")[1];
			s1 = s1.replaceAll("\"", "");
			double distance = Double.valueOf(s1);
			r.setDistanceToCustomer(distance);
			str = "";
			str2="";
		
		}
		}
		catch (MalformedURLException e) {
			
		}
		 finally {
			 in2.close();
		 }*/
	}
}



