package org.mostpates.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.mostpates.checkout.Cashier;
import org.mostpates.people.Customer;
import org.mostpates.shops.Item;
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
		Item i1 = new Item();
		i1.setName("Pizza");
		i1.setPrice(8.99);
		Item i2 = new Item();
		i2.setName("Spagettie");
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

		
		
		mySystem.addRestaurant(r1);
		r1.setName("JapaPohns");
		r1.setAddress("2545 E Speedway Blvd, Tucson, AZ 85719");
		mySystem.addRestaurant(r2);
		r2.setName("McD");
		r2.setAddress("1711 E Speedway Blvd, Tucson, AZ, 85719");
		mySystem.addRestaurant(r3);
		r3.setName("BBQ Wings");
		r3.setAddress("4329 N Oracle Rd, Tucson AZ, 85719");
		mySystem.addRestaurant(r4);
		r4.setName("SingWtop");
		r4.setAddress("3122 N Campbell Ave, Tucson AZ, 85719");
		mySystem.addRestaurant(r5);
		r5.setName("KurgerBing");
		r5.setAddress("454 W Grant Rd, Tucson, AZ, 85719");
		mySystem.addRestaurant(r6);
		r6.setName("GactusCrille");
		r6.setAddress("1303 E University Blvd, Tucson AZ, 85719");
		
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
						//String dir = "https://maps.googleapis.com/maps/api/directions/json?origin="+"userAdd"+"&destination="+"storeADd"+"&key=";

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
