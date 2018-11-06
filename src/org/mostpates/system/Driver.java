package org.mostpates.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.mostpates.checkout.Cashier;
import org.mostpates.checkout.Coupon;
import org.mostpates.people.Customer;
import org.mostpates.shops.Item;
import org.mostpates.shops.Restaurant;

public class Driver {

	public static void main(String[] args) throws IOException {
		Systems mySystem = new Systems();//make system
		
		Restaurant r1 = new Restaurant();
		Restaurant r2 = new Restaurant();
		Restaurant r3 = new Restaurant();
		Restaurant r4 = new Restaurant();
		Restaurant r5 = new Restaurant();
		Restaurant r6 = new Restaurant();
		
		Customer c1 = new Customer();
		Customer c2 = new Customer();
		Customer c3 = new Customer();
		
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
		r6.getMenu().add(i9);
		
		mySystem.addCustomer(c1);
		c1.setName("Josh");
		c1.setAddress("1920 N 1st Ave, Tucson AZ, 85719");
		mySystem.addCustomer(c2);
		c2.setName("Jessica");
		c2.setAddress("204 N Mabel Rd, Tucson AZ, 85719");
		mySystem.addCustomer(c3);
		c3.setName("Nadine");
		c3.setAddress("210 N Stone Ave, Tucson AZ, 85719");
		Scanner in = null;
		String command = null;
		String[] commandList = null;
		in = new Scanner(new File(args[0]));
		Customer c;
		Restaurant r = null;
		while(in.hasNextLine()) {
			command = in.nextLine();
			commandList = command.split(",");
			int flag = 0;
			if(commandList[0]!="#") {
				switch(commandList[0]){
					case "AddItem":
						System.out.println("AddItem");
						flag = 0;
						c = mySystem.getCustomer(commandList[1].toLowerCase());
						r = mySystem.getRestaurant(commandList[3].toLowerCase());
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
					case "Total":
						System.out.println("Total");
						c = mySystem.getCustomer(commandList[1].toLowerCase());
						System.out.println(c.getName()+ "'s total is "+c.getCart().getTotal(c.getCoupon()));
						break;
					case "Order":
						System.out.println("Order");
						//String dir = "https://maps.googleapis.com/maps/api/directions/json?origin="+"userAdd"+"&destination="+"storeADd"+"&key=";
						c = mySystem.getCustomer(commandList[1].toLowerCase());
						c.order(r);
						break;
					case "RemoveItem":
						System.out.println("RemoveItem");
						flag = 0;
						c = mySystem.getCustomer(commandList[1].toLowerCase());
						r = mySystem.getRestaurant(commandList[3].toLowerCase());
						for(Item i : r.getMenu()) {
							if(commandList[2].toLowerCase().compareTo(i.getName().toLowerCase())==0) {
								c.removeFromCart(i);
								flag = 1;
							}
														
						}
						if (flag!=1) {
						System.out.println("No "+commandList[2]+" found in " + r.getName()+ "'s cart");
						}
						break;
					case "Status":
						System.out.println("Status");
						c = mySystem.getCustomer(commandList[1].toLowerCase());
						System.out.print(c.getName() + "'s Items: ");
						for(Item i : c.getCart().getItems()) {
							System.out.print(i.getName() + " ");
						}
						System.out.println();
						break;
					case "Coupon":
						System.out.println("Coupon");
						Coupon.checkValid(commandList[2]);
						break;

				}
			}
		}
		System.out.println("***Restaurant List***");
		mySystem.printRestaurants();
		System.out.println("***Customer List/***");
		mySystem.printCustomers();
		System.out.println("***Item List***");
		mySystem.printItems();
		in.close();
		
		

	}

}
