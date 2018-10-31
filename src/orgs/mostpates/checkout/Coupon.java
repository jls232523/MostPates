package orgs.mostpates.checkout;

public class Coupon {
	String code;
	double percentOff;
	public Coupon() {
		code = "unknown";
		percentOff = 0.00;
	}
	public boolean checkCode(String code) {
		return false;
	}
	public double getPercentageOff() {
		return this.percentOff;
	}
}
