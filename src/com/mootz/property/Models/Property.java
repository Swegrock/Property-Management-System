package com.mootz.property.Models;

import java.io.*;

public class Property implements Serializable {

	private static final long serialVersionUID = 3L;

	// The type of property.
	private PropertyType propertyType;

	// Variables for both houses and flats.
	private String address;
	private int numberOfRooms;
	private float price;
	private boolean sold;

	// Variables exclusive to houses.
	private boolean hasGarden;
	private boolean hasGarage;

	// Variables exclusive to flats.
	private int floorNumber;
	private float monthlyCharge;

	// Constructor for house property.
	public Property (String address, float price, boolean sold, int numberOfRooms, boolean hasGarden, boolean hasGarage) {
		this.propertyType = PropertyType.House;
		this.address = address;
		this.numberOfRooms = numberOfRooms;
		this.price = price;
		this.sold = sold;
		this.hasGarden = hasGarden;
		this.hasGarage = hasGarage;
	}

	// Constructor for flat property.
	public Property (String address, float price, boolean sold, int numberOfRooms, int floorNumber, float monthlyCharge) {
		this.propertyType = PropertyType.Flat;
		this.address = address;
		this.numberOfRooms = numberOfRooms;
		this.price = price;
		this.sold = sold;
		this.floorNumber = floorNumber;
		this.monthlyCharge = monthlyCharge;
	}

	//The gets.

	public PropertyType getPropertyType () {
		return this.propertyType;
	}

	public String getAddress() {
		return this.address;
	}

	public int getNumberOfRooms() {
		return this.numberOfRooms;
	}

	public float getPrice() {
		return this.price;
	}

	public boolean getSold() {
		return this.sold;
	}

	public boolean getHasGarden() {
		return this.hasGarden;
	}

	public boolean getHasGarage() {
		return this.hasGarage;
	}

	public int getFloorNumber() {
		return this.floorNumber;
	}

	public float getMonthlyCharge() {
		return this.monthlyCharge;
	}

	//The sets.

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public void setHasGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
	}

	public void setHasGarage(boolean hasGarage) {
		this.hasGarage = hasGarage;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public void setMonthlyCharge(float monthlyCharge) {
		this.monthlyCharge = monthlyCharge;
	}
}
