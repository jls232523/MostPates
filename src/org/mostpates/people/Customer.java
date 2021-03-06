package org.mostpates.people;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.mostpates.checkout.*;
import org.mostpates.shops.Item;
import org.mostpates.shops.Restaurant;

import javafx.scene.control.Label;

public class Customer {
	public String name;
	public String phone;
	public String address;
	public ShoppingCart cart;
	public Coupon coupon;
	public Confirmation confirm;
	
	public Customer() {
		name ="unknown";
		phone ="0";
		address ="unknown";
		cart = new ShoppingCart();
		coupon = new Coupon();
		confirm = new Confirmation();
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

	public void addToCart(Item newItem) {
		this.getCart().addItem(newItem);
		System.out.println(newItem.getName() + " successfully added to cart.");
	}
	public ShoppingCart getCart() {
		return cart;
	}
	public ArrayList<Label> order(Restaurant r) throws IOException {
		ArrayList<Label> labels = new ArrayList<Label>();
		DecimalFormat df = new DecimalFormat("#.00"); 
		System.out.print("***ORDER PLACED***");
		try {
		String str = new String();
		String str2 = new String();
		URL direct = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+r.getAddress().replaceAll(" ", "")+"&destinations="+this.getAddress().replaceAll(" ", "")+"&key=AIzaSyBl49PQg0nL_4KAEjWXMB1hFT0xqjdTjco");
		URLConnection direcConnect = direct.openConnection();//opens a connection to GoogleMaps to get distance and time estimates
		BufferedReader in2 = new BufferedReader(new InputStreamReader(direcConnect.getInputStream()));
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
			this.confirm.setOrderTime();
			this.confirm.getEstimatedTime(num);
	
			Label time = new Label("Placed at " + this.confirm.getOrderTime());
			labels.add(time);
			//System.out.println("\nPlaced at " + this.confirm.getOrderTime());
			Label arrival = new Label("Estimated time of arrival is " + this.confirm.getEstimatedTime());
			labels.add(arrival);
			//System.out.println("Estimated time of arrival is " + this.confirm.getEstimatedTime());	
			//this.getCart().printCart();
			Label subTotal = new Label("Sub-Total is $"+this.getCart().getTotal(this.getCoupon()));
			labels.add(subTotal);
			//System.out.println("Sub-Total is $"+this.getCart().getTotal(this.getCoupon()));
			distance = this.getCart().calcDelivFee(distance) + 2.99;
			
			Label deliv = new Label("Delivery Fee is $2.99 + $0.50 per Mile ("+s1+" miles) $"+distance);
			labels.add(deliv);
			Label total = new Label("Total : " + (distance + Double.valueOf(this.getCart().getTotal(this.getCoupon()))));
			labels.add(total);
			//System.out.println("Delivery Fee is $2.99 + $0.50 per Mile ("+s1+" miles) $"+distance);
			//System.out.println("Total : " + (distance + Double.valueOf(this.getCart().getTotal(this.getCoupon()))));
			//System.out.println("Your Savings: " + this.getCart().getSavings(this.getCoupon()));
			//System.out.println("\n\n");
		}
		catch (Exception x){//if connection doesn't go through
		this.confirm.setOrderTime();
		System.out.println("\nPlaced at " + this.confirm.getOrderTime());
		System.out.println("Estimated time of arrival is " + this.confirm.getEstimatedTime());	
		}
		return labels;
	}
	public void removeFromCart(Item newItem) {
		this.getCart().removeItem(newItem);
		
	}
	public Coupon getCoupon() {
		return this.coupon;
		
	}
	public void setPercentOff(Double double1) {
		this.coupon.setPercentOff(double1);
		
	}
	public ArrayList<Label> placeOrder(Restaurant r) {
		ArrayList<Label> labels = new ArrayList<Label>();
		DecimalFormat df = new DecimalFormat("#.00"); 
		System.out.print("***ORDER PLACED***");
		try {
		String str = new String();
		String str2 = new String();
		URL direct = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+r.getAddress().replaceAll(" ", "")+"&destinations="+this.getAddress().replaceAll(" ", "")+"&key=AIzaSyBl49PQg0nL_4KAEjWXMB1hFT0xqjdTjco");
		URLConnection direcConnect = direct.openConnection();//opens a connection to GoogleMaps to get distance and time estimates
		BufferedReader in2 = new BufferedReader(new InputStreamReader(direcConnect.getInputStream()));
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
			this.confirm.setOrderTime();
			this.confirm.getEstimatedTime(num);
	
			Label time = new Label("Placed at " + this.confirm.getOrderTime());
			labels.add(time);
			//System.out.println("\nPlaced at " + this.confirm.getOrderTime());
			Label arrival = new Label("Estimated time of arrival is " + this.confirm.getEstimatedTime());
			labels.add(arrival);
			//System.out.println("Estimated time of arrival is " + this.confirm.getEstimatedTime());	
			//this.getCart().printCart();
			Label subTotal = new Label("Sub-Total is $"+this.getCart().getTotal(this.getCoupon()));
			labels.add(subTotal);
			//System.out.println("Sub-Total is $"+this.getCart().getTotal(this.getCoupon()));
			distance = this.getCart().calcDelivFee(distance) + 2.99;
			
			Label deliv = new Label("Delivery Fee is $2.99 + $0.50 per Mile ("+s1+" miles) $"+distance);
			labels.add(deliv);
			Label total = new Label("Total : " + (distance + Double.valueOf(this.getCart().getTotal(this.getCoupon()))));
			labels.add(total);
			//System.out.println("Delivery Fee is $2.99 + $0.50 per Mile ("+s1+" miles) $"+distance);
			//System.out.println("Total : " + (distance + Double.valueOf(this.getCart().getTotal(this.getCoupon()))));
			Label saving = new Label("Your Savings: " + this.getCart().getSavings(this.getCoupon()));
			labels.add(saving);
			//System.out.println("Your Savings: " + this.getCart().getSavings(this.getCoupon()));
			//System.out.println("\n\n");
		}
		catch (Exception x){//if connection doesn't go through
		this.confirm.setOrderTime();
		System.out.println("\nPlaced at " + this.confirm.getOrderTime());
		System.out.println("Estimated time of arrival is " + this.confirm.getEstimatedTime());	
		}
		return labels;
	}
}
