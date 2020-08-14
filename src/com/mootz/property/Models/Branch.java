package com.mootz.property.Models;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Branch implements Serializable, IAccount {
	
	private static final long serialVersionUID = 2L;
	
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
	@Override
	public String getUsername() {
		return this.username;
	}
	
	//Gets the branch supervisors password.
	@Override
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
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	
	//Sets the password for the branch supervisor.
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	//Adds a property
	public void addProperty(Property property) {
		this.properties.add(property);
	}
	
	//Edits a property
	public void editProperty(Property property, int index) {
		this.properties.add(index, property);
		this.properties.remove(index + 1);
	}
	
	//Removes a property.
	public void removeProperty(Property property) {
		this.properties.remove(property);
	}
	
	//Gets a property from list.
	public Property getProperty(int index) {
		return this.properties.get(index);
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
	
	public ArrayList<Property> getProperties(String address, boolean sold){
		return getPropertyArrayList(this.properties.stream().filter(x -> x.getSold() == sold
				&& x.getAddress().contains(address)));
	}
	
	//Gets an array list of the properties marked flats, the variable sold determines whether the properties should be sold or not.
	public ArrayList<Property> getFlats(String address, boolean sold) {
		return getPropertyArrayList(this.properties.stream().filter(x -> x.getPropertyType() == PropertyType.Flat
				&& x.getSold() == sold
				&& x.getAddress().contains(address)));
	}
	
	//Gets an array list of the properties marked houses, the variable sold determines whether the properties should be sold or not.
	public ArrayList<Property> getHouses(String address, boolean sold) {
		return getPropertyArrayList(this.properties.stream().filter(x -> x.getPropertyType() == PropertyType.House
				&& x.getSold() == sold
				&& x.getAddress().contains(address)));
	}
	
	//Gets an array list of properties from a stream.
	private ArrayList<Property> getPropertyArrayList(Stream<Property> stream) { 
		List<Property> list = stream.collect(Collectors.toList()); 
	    ArrayList<Property> arrayList = new ArrayList<Property>(list); 
	    return arrayList; 
	} 
}
