package org.mostpates.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.mostpates.checkout.Cashier;
import org.mostpates.people.Customer;
import org.mostpates.shops.Restaurant;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		System mySystem = new System();//make system
		
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
		
		
		mySystem.addRestaurant(r1);
		mySystem.addRestaurant(r2);
		mySystem.addRestaurant(r3);
		mySystem.addRestaurant(r4);
		mySystem.addRestaurant(r5);
		mySystem.addRestaurant(r6);
		
		mySystem.addCustomer(c1);
		mySystem.addCustomer(c2);
		mySystem.addCustomer(c3);
		
		Scanner in = null;
		String command = null;
		String[] commandList = null;
		in = new Scanner(new File(args[0]));
		while(in.hasNextLine()) {
			command = in.nextLine();
			commandList = command.split(",");
			if(commandList[0]!="#") {
				switch(commandList[0]){
					case "AddItem":
						break;
					case "Total":
						break;
					case "Order":
						break;
					case "RemoveItem":
						break;
					case "Status":
						break;
					case "Coupon":
						break;

				}
			}
		}
		
		in.close();
		
		

	}

}
