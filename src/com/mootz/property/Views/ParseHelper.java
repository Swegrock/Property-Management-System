package com.mootz.property.Views;

public class ParseHelper {
	public static boolean TryParseFloat(String f) {
		try {
			Float.parseFloat(f);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static boolean TryParseInt(String i) {
		try {
			Integer.parseInt(i);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
