package com.mootz.property.Views;

public class ParseHelper {
	// Tries to parse a float and returns true if possible, false if not.
	public static boolean TryParseFloat(String f) {
		try {
			Float.parseFloat(f);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	//Tries to parse an integer and returns true if possible, false if not.
	public static boolean TryParseInt(String i) {
		try {
			Integer.parseInt(i);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
