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
		//mySystem.buildSystem();
		/*Restaurant r1 = new Restaurant();
		Restaurant r2 = new Restaurant();
		Restaurant r3 = new Restaurant();
		Restaurant r4 = new Restaurant();
		Restaurant r5 = new Restaurant();
		Restaurant r6 = new Restaurant();

		
		Cashier ca1 = new Cashier();
		Item i1 = new Item();
		i1.setName("Pizza");
		i1.setPrice(8.99);
		Item i2 = new Item();
		i2.setName("Spagettie and Meatballs");
		i2.setPrice(7.99);
		Item i3 = new Item();
		i3.setName("BBQ Wings");
		i3.setPrice(8.99);
		Item i4 = new Item();
		i4.setName("Coke");
		i4.setPrice(1.99);
		Item i5 = new Item();
		i5.setName("Breadsticks");
		i5.setPrice(3.99);
		Item i6 = new Item();
		i6.setName("Sprite");
		i6.setPrice(1.99);
		Item i7 = new Item();
		i7.setName("Orange Chicken");
		i7.setPrice(10.99);
		Item i8 = new Item();
		i8.setName("Ramen");
		i8.setPrice(0.99);
		Item i9 = new Item();
		i9.setName("Chicken Noodle Soup");
		i9.setPrice(3.99);
		Item i10 = new Item();
		i10.setName("White Rice");
		i10.setPrice(1.99);
		Item i11 = new Item();
		i11.setName("Mac and Cheese");
		i11.setPrice(12.99);
		
		
		mySystem.addRestaurant(r1);
		r1.getMenu().add(i1);
		r1.getMenu().add(i5);
		r1.setName("JapaPohns");
		r1.setAddress("2545 E Speedway Blvd, Tucson, AZ 85719");
		
		mySystem.addRestaurant(r2);
		r2.setName("McD");
		r2.getMenu().add(i4);
		r2.setAddress("1711 E Speedway Blvd, Tucson, AZ, 85719");
		
		mySystem.addRestaurant(r3);
		r3.setName("Bapplebees");
		r3.getMenu().add(i3);
		r3.setAddress("4329 N Oracle Rd, Tucson AZ, 85719");
		
		mySystem.addRestaurant(r4);
		r4.setName("SingWtop");
		r4.getMenu().add(i3);
		r4.setAddress("3122 N Campbell Ave, Tucson AZ, 85719");
		
		mySystem.addRestaurant(r5);
		r5.setName("GliveOardin");
		r5.setAddress("454 W Grant Rd, Tucson, AZ, 85719");
		r5.getMenu().add(i2);
		r5.getMenu().add(i5);
		
		mySystem.addRestaurant(r6);
		r6.setName("GactusCrille");
		r6.setAddress("1303 E University Blvd, Tucson AZ, 85719");
		r6.getMenu().add(i6);
		r6.getMenu().add(i9);*/

		Scanner in = null;
		String command = null;
		String[] commandList = null;
		in = new Scanner(System.in);
		
		String inpt = "hello";
		Customer c;
		Restaurant r;
		System.out.println("***Welcome to MostPates***\nWho is trying to order food?\n");
		String userIn = in.nextLine();
		Customer c1 = new Customer();
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
		r.printMenu();
		while(inpt.toLowerCase().compareTo("exit")!=0) {
			inpt = in.nextLine().toLowerCase();
			int flag = 0;
				switch(inpt){
					case "additem":
						System.out.println("AddItem");
						flag = 0;
						c = mySystem.getCustomer(commandList[1]);
						r = mySystem.getRestaurant(commandList[3]);
						for(Item i : r.getMenu()) {
							if(commandList[2].compareTo(i.getName())==0) {
								c.addToCart(i);
								flag = 1;
								break;
							}
						}
						if (flag!=1) {
						System.out.println("No "+commandList[2]+" found in " + r.getName()+ "'s menu");
						}
						break;
					case "total":
						System.out.println("Total");
						c = mySystem.getCustomer(commandList[1]);
						System.out.println(c.getName()+ "'s total is "+c.getCart().getTotal());
						break;
					case "order":
						System.out.println("Order");
						//String dir = "https://maps.googleapis.com/maps/api/directions/json?origin="+"userAdd"+"&destination="+"storeADd"+"&key=";
						c = mySystem.getCustomer(commandList[1]);
						c.order();
						break;
					case "removeitem":
						System.out.println("RemoveItem");
						flag = 0;
						c = mySystem.getCustomer(commandList[1]);
						r = mySystem.getRestaurant(commandList[3]);
						for(Item i : r.getMenu()) {
							if(commandList[2].compareTo(i.getName())==0) {
								c.removeFromCart(i);
								flag = 1;
							}
														
						}
						if (flag!=1) {
						System.out.println("No "+commandList[2]+" found in " + r.getName()+ "'s cart");
						}
						break;
					case "status":
						System.out.println("Status");
						c = mySystem.getCustomer(commandList[1]);
						System.out.print(c.getName() + "'s Items: ");
						for(Item i : c.getCart().getItems()) {
							System.out.print(i.getName() + " ");
						}
						System.out.println();
						break;
					case "coupon":
						System.out.println("Coupon");
						Coupon.checkValid(commandList[2]);
						break;

				}
			}
		}
	}

			
		

