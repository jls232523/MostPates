package org.mostpates.checkout;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coupon {
	String code;
	double percentOff;
	private static Map<String,Double> validCodes;
	public Coupon() {
		code = "unknown";
		percentOff = 0.00;
		validCodes = new HashMap<String,Double>();
		validCodes.put("wildcat",20.0);
		validCodes.put("ece",15.0);
	}
	public boolean checkCode(String code) {
		return false;
	}
	public double getPercentageOff() {
		return this.percentOff;
	}
	public static boolean checkValid(String string) {
		for(String s : getValidCodes().keySet()) {
			if(string.toLowerCase().compareTo(s.toLowerCase())==0) {
				return true;
			}
		}
		return false;
	}
	public String getName() {
		return this.code;
	}
	public void setName(String inpt) {
		this.code = inpt;
		
	}
	public static Map<String,Double> getValidCodes() {
		return validCodes;
	}
	public static void setValidCodes(Map<String,Double> validCodes) {
		Coupon.validCodes = validCodes;
	}
	public void setPercentOff(Double double1) {
		this.percentOff = double1;
		
	}
}
