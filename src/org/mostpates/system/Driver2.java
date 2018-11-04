package org.mostpates.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.sql.*;
import org.mostpates.checkout.Cashier;
import org.mostpates.checkout.Coupon;
import org.mostpates.people.Customer;
import org.mostpates.shops.Item;
import org.mostpates.shops.Restaurant;

public class Driver2 {
	public static void main(String[] args) throws FileNotFoundException {

		Systems mySystem = new Systems();//make system
		mySystem.buildSystem(args);

		Scanner in = null;
		String command = null;
		String[] commandList = null;
		in = new Scanner(System.in);
		
		String userIn = "hello";
		Customer c;
		Restaurant r;
		System.out.println("***Welcome to MostPates***\nWho is trying to order food?\n");
		userIn = in.nextLine();
		Customer c1 = new Customer();
		int flag = 0;
		
		c1.setName(userIn);
		System.out.println("Where do you live?\n");
		userIn = in.nextLine();
		c1.setAddress(userIn);
		System.out.println("Phone Number?\n");
		userIn = in.nextLine();
		c1.setPhone(userIn);
		mySystem.addCustomer(c1);
		System.out.println("***Restaurant List***");
		mySystem.printRestaurants();
		System.out.println("Which restaurant do you want to order from?");
		userIn = in.nextLine();
		r = mySystem.getRestaurant(userIn.toLowerCase());
		Item i;
		while(userIn.toLowerCase().compareTo("exit")!=0) {
			r.printMenu();
			flag = 0;
			System.out.println("Which Item do you want?(or cart to see cart,order to place order,coupon to enter coupon code,back to go back to restaurants,remove to remove an item or exit to cancel order and exit");
			userIn = in.nextLine().toLowerCase();
			if(userIn.toLowerCase().compareTo("exit")==0) {
				break;
			}
			if(!(userIn.toLowerCase().compareTo("cart")==0 ||userIn.toLowerCase().compareTo("order")==0 ||userIn.toLowerCase().compareTo("coupon")==0 ||userIn.toLowerCase().compareTo("exit")==0||userIn.toLowerCase().compareTo("back")==0||userIn.toLowerCase().compareTo("remove")==0)) {
			i = r.getItem(userIn);
			while(i==null) {
				System.out.println("Not a valid choice please pick again");
				userIn = in.nextLine().toLowerCase();
				if((userIn.toLowerCase().compareTo("cart")==0 ||userIn.toLowerCase().compareTo("order")==0 ||userIn.toLowerCase().compareTo("coupon")==0 ||userIn.toLowerCase().compareTo("exit")==0||userIn.toLowerCase().compareTo("back")==0||userIn.toLowerCase().compareTo("remove")==0)) {
					flag = 1;
					break;
				}
				i = r.getItem(userIn);
			}
			if(flag==0) {
			c1.addToCart(i);
			}
			}
			if(userIn.toLowerCase().compareTo("cart")==0) {
				c1.getCart().printCart();
				System.out.println("Current Total is $"+c1.getCart().getTotal());
				System.out.println("\n\n");
			}
			if(userIn.toLowerCase().compareTo("remove")==0) {
				c1.getCart().printCart();
				if(c1.getCart().getItems().size()==0) {
					System.out.println("No items in cart cannot remove");
				}
				else {
				System.out.println("Which item do you want to remove?");
				userIn = in.nextLine().toLowerCase();
				i = r.getItem(userIn);
				while(!(c1.getCart().getItems().contains(i))) {
					System.out.println("Not a valid choice please pick again");
					userIn = in.nextLine().toLowerCase();
					if((userIn.toLowerCase().compareTo("cart")==0 ||userIn.toLowerCase().compareTo("order")==0 ||userIn.toLowerCase().compareTo("coupon")==0 ||userIn.toLowerCase().compareTo("exit")==0||userIn.toLowerCase().compareTo("back")==0||userIn.toLowerCase().compareTo("remove")==0)) {
						flag = 1;
						break;
					}
					i = r.getItem(userIn);
				}
				if(flag==0) {
				System.out.println(i.getName() + " Successfully removed from cart.");
				c1.removeFromCart(i);
				c1.getCart().printCart();
				}
				}
				
			}
			if(userIn.toLowerCase().compareTo("order")==0) {
				c1.order();
				break;
			}
			if(userIn.toLowerCase().compareTo("coupon")==0) {
				Coupon temp = c1.getCoupon();
				if(temp.getName().compareTo("unknown")==0) {
				System.out.print("Enter in your coupon code");
				userIn = in.nextLine().toLowerCase();
				if(Coupon.checkValid(userIn)) {
					flag =1;
					c1.getCoupon().setName(userIn);
					c1.setPercentOff(Coupon.getValidCodes().get(userIn));
					System.out.println(c1.getCoupon().getName() + " successfully added to your account " + c1.getCoupon().getPercentageOff() + "% will be taken off at checkout.");
				}
				else {
					System.out.println("Not a valid code please try again or type back to go back");
					userIn = in.nextLine().toLowerCase();
					if(userIn.compareTo("back")!=0) {
						while(!(Coupon.checkValid(userIn))) {
							if(userIn.toLowerCase().compareTo("back")==0) {
								break;
							}
							System.out.println("Not a valid code please try again");
							userIn = in.nextLine().toLowerCase();
							
						}
					}
				}
				if(Coupon.checkValid(userIn) && flag !=1) {
					c1.getCoupon().setName(userIn);
					c1.setPercentOff(Coupon.getValidCodes().get(userIn));
					System.out.println(c1.getCoupon().getName() + " successfully added to your account " + c1.getCoupon().getPercentageOff() + "% will be taken off at checkout.");
				}
				}
				else {
					System.out.println("You already have an active coupon code.");
				}
				
			}

			if(userIn.toLowerCase().compareTo("back")==0) {
				System.out.println("***Restaurant List***");
				mySystem.printRestaurants();
				System.out.println("Which restaurant do you want to order from?");
				userIn = in.nextLine();
				r = mySystem.getRestaurant(userIn.toLowerCase());
				while(r==null) {
					if(userIn.toLowerCase().compareTo("exit")==0) {
						break;
					}
					System.out.println("Not a valid choice please pick again");
					userIn = in.nextLine().toLowerCase();
					r = mySystem.getRestaurant(userIn.toLowerCase());
				}
			}
		}
	}
	}

			
		

