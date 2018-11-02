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
		r1.setName("JapaPohns");
		mySystem.addRestaurant(r2);
		r2.setName("McD");
		mySystem.addRestaurant(r3);
		r3.setName("BBQ Wings");
		mySystem.addRestaurant(r4);
		r4.setName("SingWtop");
		mySystem.addRestaurant(r5);
		r5.setName("KurgerBing");
		mySystem.addRestaurant(r6);
		r6.setName("GactusCrille");
		
		mySystem.addCustomer(c1);
		c1.setName("Josh");
		mySystem.addCustomer(c2);
		c2.setName("Jessica");
		mySystem.addCustomer(c3);
		c3.setName("Nadine");
		
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
