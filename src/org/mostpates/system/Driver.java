package org.mostpates.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.mostpates.checkout.Coupon;
import org.mostpates.people.Customer;
import org.mostpates.shops.Item;
import org.mostpates.shops.Restaurant;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Driver {
	public static void main(String[] args) throws IOException {
		Systems mySystem = new Systems();//make system
		mySystem.buildSystem();
		Scanner in = null;
		in = new Scanner(System.in);
		File currentDir = new File("");
	 	String path = currentDir.getAbsolutePath() + "/src/OutputFiles/users.txt";
		PrintWriter userFile = new PrintWriter(new BufferedWriter(new FileWriter(path,true)));
		String userIn = "hello";
		Restaurant r = null;
		Customer c1 = new Customer();
		int flag = 0;
		int check = 0;
		int userCheck = 0;
		System.out.println("***Welcome to MostPates***\nWho is trying to order food?(n for new user or any key for returning)\n");
		userIn = in.nextLine();
		if(userIn.toLowerCase().replaceAll("\\s+", "").compareTo("n")==0) {
			//Driver.makeNewCustomer(userIn, userFile, c1, in, mySystem); //makes new customer 
		}
		else {
			System.out.println("What is your name?\n");
			userIn = in.nextLine().toLowerCase().replaceAll("\\s+","");
			userCheck = Driver.checkExistingCustomer(userIn, c1, userCheck);
			if(userCheck==0) { //loop to keep asking for username
			System.out.println("Seems like there is not an account with that name enter another or press n to create an account");
			userIn = in.nextLine().toLowerCase().replaceAll("\\s+","");
			userCheck = Driver.checkExistingCustomer(userIn, c1, userCheck);
			while(userIn.compareTo("n")!=0 && userCheck == 0) {
			System.out.println("Seems like there is not an account with that name enter another or press n to create an account");
			userIn = in.nextLine().toLowerCase().replaceAll("\\s+","");
			userCheck = Driver.checkExistingCustomer(userIn, c1, userCheck);
			}
			}
			if(userCheck==0) {
				//Driver.makeNewCustomer(userIn, userFile, c1, in, mySystem); //make new customer in system
			}
		}
		
		userFile.close();
		r = Driver.back(mySystem, userIn, r, in,c1);
		Item i = null;
		while(userIn.toLowerCase().replaceAll("\\s+","").compareTo("exit")!=0) { //main loop to keep function going
			if(check==0 && r!=null) {
			r.printMenu();
			flag = 0;
			System.out.println("\nWhich would you like to do?(add, cart, order, coupon, back, remove, or exit)");
			userIn = in.nextLine().toLowerCase().replaceAll("\\s+","");;
			}
			if(userIn.toLowerCase().replaceAll("\\s+","").compareTo("exit")==0) {
				//customer is done with app
				break;
			}
			else if(userIn.toLowerCase().replaceAll("\\s+","").compareTo("add")==0) { //customer wants to add an item 
				//Driver.addItem(i, r, userIn, flag, c1, in);
				check = 0;
			}
			else if(userIn.toLowerCase().replaceAll("\\s+","").compareTo("cart")==0) { // customer wants to see their cart
				c1.getCart().printCart();
				System.out.println("Current Total is $"+c1.getCart().getTotal(c1.getCoupon()));
				System.out.println("Your Savings: " + c1.getCart().getSavings(c1.getCoupon()));
				System.out.println("\n\n");
				check = 0;
			}
			else if(userIn.toLowerCase().replaceAll("\\s+","").compareTo("remove")==0) { //customer wants to remove an item
				Driver.remove(c1, userIn, i, r, in, flag);
				check = 0;
			}
			else if(userIn.toLowerCase().replaceAll("\\s+","").compareTo("order")==0) {//customer wants to place an order
				if(c1.getCart().getItems().size()!=0) {
				c1.order(r);
				check = 0;
				break;
				}
				else {
					System.out.println("***No Items in Cart cannot complete order.***");
					System.out.println("\nWhich would you like to do?(add, cart, order, coupon, back, remove, or exit)");
					userIn = in.nextLine().toLowerCase().replaceAll("\\s+", "");
				}
			}
			else if(userIn.toLowerCase().replaceAll("\\s+","").compareTo("coupon")==0) {// customer wants to use a coupon code
				Driver.coupon(c1, userIn, null);	
				check = 0;
			}
			else if(userIn.toLowerCase().replaceAll("\\s+","").compareTo("back")==0) {//customer wants to go back to restaurant list
				r = Driver.back(mySystem, userIn, r, in,c1);
				check = 0;
			}
			else {
				while(!(userIn.toLowerCase().replaceAll("\\s+","").compareTo("back")==0||userIn.toLowerCase().replaceAll("\\s+","").compareTo("coupon")==0||userIn.toLowerCase().replaceAll("\\s+","").compareTo("order")==0||userIn.toLowerCase().replaceAll("\\s+","").compareTo("remove")==0||userIn.toLowerCase().replaceAll("\\s+","").compareTo("cart")==0||userIn.toLowerCase().replaceAll("\\s+","").compareTo("add")==0)) {
				if(userIn.toLowerCase().replaceAll("\\s+", "").compareTo("exit")==0) {//bad command ask for it again
					break;
				}
				System.out.println("\nWhich would you like to do?(add, cart, order, coupon, back, remove, or exit)");
				userIn = in.nextLine().toLowerCase().replaceAll("\\s+", "");
				check = 1;
			}
			}
			flag = 0;
		}
	}

	public static int checkExistingCustomer(String userIn,Customer c1,int userCheck) throws FileNotFoundException {//checks if the customer already exists in the system
		File currentDir = new File("");
		Scanner userScanner = new Scanner(new File(currentDir.getAbsolutePath() + "/src/OutputFiles/users.txt"));
		while(userScanner.hasNextLine()) {
			String userLine = userScanner.nextLine();
			String[] userLineList = userLine.split(",");
			if(userLineList[0].toLowerCase().replaceAll("\\s+","").compareTo(userIn.toLowerCase().replaceAll("\\s+", ""))==0) {
				c1.setName(userLineList[0]);
				c1.setAddress(userLineList[1]);
				c1.setPhone(userLineList[2]);
				System.out.println("Welcome Back "+ c1.getName());
				userCheck = 1;
			}
		}
		userScanner.close();
		return userCheck;
	}

	public static void makeNewCustomer(String name,String addr,String phone,PrintWriter userFile,Customer c1,Scanner in,Systems mySystem) {//makes new customer for system through userIn
		userFile.append("\n");
		c1.setName(name.replaceAll(",",""));
		//userFile.print(name + ",");
		userFile.append(name + ",");

		c1.setAddress(addr.replaceAll(",", ""));
		//userFile.print(addr + ",");
		userFile.append(addr + ",");
		c1.setPhone(phone.replaceAll(",", ""));
		//userFile.print(phone+"\n");
		userFile.append(phone + ",");
		mySystem.addCustomer(c1);
		userFile.close();
		
	}

	public static Restaurant back(Systems mySystem, String userIn, Restaurant r, Scanner in, Customer c1) {//goes back to restaurant screen
		System.out.println("***Restaurant List***");
		mySystem.printRestaurants();
		System.out.println("Which restaurant do you want to order from?");
		userIn = in.nextLine();
		r = mySystem.getRestaurant(userIn.toLowerCase());
		if( r!=null&&!(Driver.checkCart(c1,r)) ) {
			System.out.println("WARNING: Cart has been erased");
			c1.getCart().eraseCart();
		}
		while(r==null) {
			if(userIn.toLowerCase().compareTo("exit")==0) {
				System.exit(1);
			}
			System.out.println("***Not a valid choice please pick again***");
			userIn = in.nextLine().toLowerCase();
			r = mySystem.getRestaurant(userIn.toLowerCase());
			if(r!=null &&!(Driver.checkCart(c1,r))) {
				System.out.println("WARNING: Cart has been erased");
				c1.getCart().eraseCart();
			}
		}
		return r;
	}

	public static boolean checkCart(Customer c1, Restaurant r) {
		
		for(Item i : c1.getCart().getItems()) {
			if(!(r.getMenu().contains(i))) {
				return false;
			}
		}
		return true;
	}

	public static void coupon(Customer c1, String userIn, Alert confirmAlert) {//determines if coupon code is valid
		Coupon temp = c1.getCoupon();

		if(Coupon.checkValid(userIn)) {
			c1.getCoupon().setName(userIn);
			c1.setPercentOff(Coupon.getValidCodes().get(userIn));
			confirmAlert.setHeaderText("Coupon Entered Successfully");
			confirmAlert.setContentText(c1.getCoupon().getPercentageOff() + "% will be taken off at checkout.");
			confirmAlert.showAndWait();
		
		}
		else {
			confirmAlert.setHeaderText("Coupon Not Valid");
			confirmAlert.setContentText("Not a valid code.");
			confirmAlert.showAndWait();
					
				}
	
	}

	public static void remove(Customer c1, String userIn, Item i,Restaurant r,Scanner in, int flag) {//removes item from cart
		c1.getCart().printCart();
		if(c1.getCart().getItems().size()==0) {
			System.out.println("No items in cart cannot remove");
		}
		else {
		System.out.println("Which item do you want to remove?(or back to go back)");
		userIn = in.nextLine().toLowerCase().replaceAll("\\s+", "");
		if(userIn.toLowerCase().replaceAll("\\s+", "").compareTo("back")==0) {
			flag = 1;	
		}
		i = r.getItem(userIn);
		while(!(c1.getCart().getItems().contains(i))&&flag==0) {
			System.out.println("Not a valid choice please pick again (or back to go back)");
			userIn = in.nextLine().toLowerCase().replaceAll("\\s+", "");
			if(userIn.toLowerCase().replaceAll("\\s+", "").compareTo("back")==0) {
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

	public static void addItem(Item i,Restaurant r, Customer c1, Alert confirmAlert) {//add items to a cart
		c1.addToCart(i);
		confirmAlert.setAlertType(AlertType.INFORMATION);
		confirmAlert.setHeaderText("Item Succesfully Added To Cart");
		confirmAlert.setContentText(i.getName() + " was added to your cart for $" + i.getPrice());
		confirmAlert.showAndWait();
	}

	public static void remove(Customer c, Item i, Restaurant r, Alert confirmAlert) {
		c.removeFromCart(i);
		confirmAlert.setAlertType(AlertType.INFORMATION);
		confirmAlert.setHeaderText("Item Succesfully Removed From Cart");
		confirmAlert.setContentText(i.getName() + " was Removed from your cart");
		confirmAlert.showAndWait();
	}
	}

			
		

