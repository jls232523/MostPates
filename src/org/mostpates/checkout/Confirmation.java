package org.mostpates.checkout;

import org.mostpates.people.Customer;

public class Confirmation {
	public boolean hasArrived;
	public double timeRemaining;
	public Confirmation(){
		hasArrived = false;
		timeRemaining = 0.00;
	}
	public boolean getStatusUpdate() {
		return this.hasArrived;
	}
	public double getTimeRemaining() {
		return this.timeRemaining;
	}
	public void setTimeArrival(double timeR) {
		this.timeRemaining = timeR;
	}
}
