package org.mostpates.checkout;

import java.util.Date;

import org.mostpates.people.Customer;

public class Confirmation {
	public boolean hasArrived;
	public long timeRemaining;
	public long timeOrdered;
	public String orderTime;
	public String estimatedArrivalTime;
	public Date date;
	public Confirmation(){
		hasArrived = false;
		timeRemaining = 0;
		timeOrdered = 0;
		date = new Date();
		orderTime = "";
		estimatedArrivalTime = "";
	}
	public boolean getStatusUpdate() {
		return this.hasArrived;
	}
	public double getTimeRemaining() {
		return this.timeRemaining;
	}
	public void setTimeArrival() {
		this.timeRemaining = this.timeOrdered + 2500;
		String currDate = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm").format(new java.util.Date (this.timeRemaining*1000));
		this.estimatedArrivalTime = currDate;
	}
	public void setOrderTime() {
		this.timeOrdered = System.currentTimeMillis()/1000;
		String currDate = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm").format(new java.util.Date (timeOrdered*1000));
		this.orderTime = currDate;
		this.setTimeArrival();
	}
	public String getOrderTime() {
		return this.orderTime;
	}
	public String getEstimatedTime() {
		return this.estimatedArrivalTime;
	}
}
