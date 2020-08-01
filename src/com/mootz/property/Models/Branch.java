package com.mootz.property.Models;

import java.util.*;
import java.util.stream.*;


public class Branch {
	private String name;
	private String address;
	private String phoneNumber;
	private String emailAddress;
	private String webAddress;
	
	private String username;
	private String password;
	
	private ArrayList<Property> properties;
	
	public Branch(String name, String address, String phoneNumber, String emailAddress, String webAddress, String username, String password) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.webAddress = webAddress;
		this.username = username;
		this.password = password;
		this.properties = new ArrayList<Property>();
	}
	
	public Branch(String filePath) {
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public String getWebAddress() {
		return this.webAddress;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<Property> getProperties(boolean sold) {
		return getPropertyArrayList(this.properties.stream().filter(x -> x.getSold() == sold));
	}
	
	public ArrayList<Property> getFlats(boolean sold) {
		return getPropertyArrayList(this.properties.stream().filter(x -> x.getPropertyType() == PropertyType.Flat
				&& x.getSold() == sold));
	}
	
	public ArrayList<Property> getHouses(boolean sold) {
		return getPropertyArrayList(this.properties.stream().filter(x -> x.getPropertyType() == PropertyType.House
				&& x.getSold() == sold));
	}
	
	public ArrayList<Property> getPropertyArrayList(Stream<Property> stream) { 
		List<Property> list = stream.collect(Collectors.toList()); 
	    ArrayList<Property> arrayList = new ArrayList<Property>(list); 
	    return arrayList; 
	} 
}
