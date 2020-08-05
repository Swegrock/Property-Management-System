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
	
	//Create a brand new branch.
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
	
	//Load a branch from a file.
	public Branch(String filePath) {
		
	}
	
	//Get the branch name.
	public String getName() {
		return this.name;
	}
	
	//Get the branch address.
	public String getAddress() {
		return this.address;
	}
	
	//Get the branch phone number.
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	//Get the branch email address.
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	//Get the branch web address.
	public String getWebAddress() {
		return this.webAddress;
	}
	
	//Gets the branch supervisors username.
	public String getUsername() {
		return this.username;
	}
	
	//Gets the branch supervisors password.
	public String getPassword() {
		return this.password;
	}
	
	//Sets the name of the branch.
	public void setName(String name) {
		this.name = name;
	}
	
	//Sets the address of the branch.
	public void setAddress(String address) {
		this.address = address;
	}
	
	//Sets the phone number of the branch.
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	//Sets the email address of the branch.
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	//Sets the web address of the branch.
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
	
	//Sets the user name of the branch supervisor.
	public void setUsername(String username) {
		this.username = username;
	}
	
	//Sets the password for the branch supervisor.
	public void setPassword(String password) {
		this.password = password;
	}
	
	//Adds a property
	public void addProperty(Property property) {
		this.properties.add(property);
	}
	
	//Removes a property.
	public void removeProperty(Property property) {
		this.properties.remove(property);
	}
	
	//Gets an array list of all the properties.
	public ArrayList<Property> getAllProperties() {
		return this.properties;
	}
	
	//Gets an array list of the properties marked houses, the variable sold determines whether the properties should be sold or not.
	public ArrayList<Property> getProperties(boolean sold) {
		return getPropertyArrayList(this.properties.stream().filter(x -> x.getSold() == sold));
	}
	
	//Gets an array list of the properties marked flats, the variable sold determines whether the properties should be sold or not.
	public ArrayList<Property> getFlats(boolean sold) {
		return getPropertyArrayList(this.properties.stream().filter(x -> x.getPropertyType() == PropertyType.Flat
				&& x.getSold() == sold));
	}
	
	//Gets an array list of the properties marked houses, the variable sold determines whether the properties should be sold or not.
	public ArrayList<Property> getHouses(boolean sold) {
		return getPropertyArrayList(this.properties.stream().filter(x -> x.getPropertyType() == PropertyType.House
				&& x.getSold() == sold));
	}
	
	//Gets an array list of properties from a stream.
	public ArrayList<Property> getPropertyArrayList(Stream<Property> stream) { 
		List<Property> list = stream.collect(Collectors.toList()); 
	    ArrayList<Property> arrayList = new ArrayList<Property>(list); 
	    return arrayList; 
	} 
}
