package org.mostpates.checkout;

public class Cashier {
	public double total;
	public double tax;
	public double tip;
	public Cashier() {
		total=0.00;
		tax = 0.00;
		tip = 0.00;
	}
	public double getTotal() {
		return this.total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getTax() {
		return this.tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTip() {
		return this.tip;
	}
	public void setTip(double tip) {
		this.tip = tip;
	}
}
