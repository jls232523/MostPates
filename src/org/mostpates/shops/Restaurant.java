package org.mostpates.shops;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Restaurant implements Comparable<Restaurant> {
	String name;
	String address;
	List<Item> menu;
	Double distanceToCustomer;
	ImageView image;
	String dist;
	SimpleStringProperty distance;
	
	public Restaurant() {
		name = "unknown";
		address = "unknown";
		menu = new ArrayList<Item>();
		distanceToCustomer = 0.0;
		dist = "";
		
		
	}
	public void setImage(String path) throws FileNotFoundException {
		File currentDir = new File("");
		path = currentDir.getAbsolutePath() + "/src/InputFiles/" + path;
		Image image = new Image(new FileInputStream(path));
		ImageView imageView = new ImageView(image);
		this.image = imageView;
		
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDistanceToCustomer(Double dtc) {
		this.distanceToCustomer = dtc;
		dist = String.valueOf(distanceToCustomer);
        this.distance = new SimpleStringProperty(dist);
      
	}
	public String getDistance() {
		return this.dist + " miles away";
	}
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Item> getMenu() {
		return this.menu;
	}
	public void setMenu(Item i) {
		this.menu.add(i);
	}
	public void printMenu() {
		int count = 1;
		System.out.println(this.name+"'s menu:");
		for(Item i : this.menu) {
		System.out.print("\t" + i.getName() + ":" + i.getPrice() + "\t");
		if(count%4==0) {
			System.out.println();
		}
		count+=1;
		}
		
	}
	public Item getItem(String inpt) {
		for(Item i : this.getMenu()) {
			if(inpt.toLowerCase().compareTo(i.getName().toLowerCase().replaceAll("\\s+",""))==0) {
				return i;
			}
		}
		return null;
	}
	@Override
	public int compareTo(Restaurant o) {
		if(this.distanceToCustomer<o.distanceToCustomer) {
			return -1;
		}
		else if(this.distanceToCustomer>o.distanceToCustomer) {
			return 1;
		}
		else {
		return 0;
		}
	}

}
