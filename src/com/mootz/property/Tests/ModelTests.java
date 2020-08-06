package com.mootz.property.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.Assert;

import com.mootz.property.Models.*;

class ModelTests {
	
	static String testName = "Name";
	static String testAddress = "Address";
	static String testPhoneNumber = "Phone Number";
	static String testEmailAddress = "Email Address";
	static String testWebAddress = "Web Address";
	static String testUsername = "Username";
	static String testPassword = "Password";
	
	static String testSetName = "Set Name";
	static String testSetAddress = "Set Address";
	static String testSetPhoneNumber = "Set Phone Number";
	static String testSetEmailAddress = "Set Email Address";
	static String testSetWebAddress = "Set Web Address";
	static String testSetUsername = "Set Username";
	static String testSetPassword = "Set Password";

	@Test
	void AdminTestGetAndSetValues() {
		Admin admin = getAdmin();
		
		Assert.assertEquals(testName, admin.getName());
		Assert.assertEquals(testUsername, admin.getUsername());
		Assert.assertEquals(testPassword, admin.getPassword());
		
		admin.setName(testSetName);
		admin.setUsername(testSetUsername);
		admin.setPassword(testSetPassword);
		
		Assert.assertEquals(testSetName, admin.getName());
		Assert.assertEquals(testSetUsername, admin.getUsername());
		Assert.assertEquals(testSetPassword, admin.getPassword());
	}
	
	@Test
	void BranchTestGetAndSetValues() {
		Branch branch = getBranch();
		
		Assert.assertEquals(testName, branch.getName());
		Assert.assertEquals(testAddress, branch.getAddress());
		Assert.assertEquals(testPhoneNumber, branch.getPhoneNumber());
		Assert.assertEquals(testEmailAddress, branch.getEmailAddress());
		Assert.assertEquals(testWebAddress, branch.getWebAddress());
		Assert.assertEquals(testUsername, branch.getUsername());
		Assert.assertEquals(testPassword, branch.getPassword());
		
		branch.setName(testSetName);
		branch.setAddress(testSetAddress);
		branch.setPhoneNumber(testSetPhoneNumber);
		branch.setEmailAddress(testSetEmailAddress);
		branch.setWebAddress(testSetWebAddress);
		branch.setUsername(testSetUsername);
		branch.setPassword(testSetPassword);
		
		Assert.assertEquals(testSetName, branch.getName());
		Assert.assertEquals(testSetAddress, branch.getAddress());
		Assert.assertEquals(testSetPhoneNumber, branch.getPhoneNumber());
		Assert.assertEquals(testSetEmailAddress, branch.getEmailAddress());
		Assert.assertEquals(testSetWebAddress, branch.getWebAddress());
		Assert.assertEquals(testSetUsername, branch.getUsername());
		Assert.assertEquals(testSetPassword, branch.getPassword());
	}
	
	@Test
	void HouseTestGetAndSetValues() {
		Property house = getHouse();
		
		Assert.assertEquals(testAddress, house.getAddress());
		Assert.assertEquals(0.0f, house.getPrice(), 0.001f);
		Assert.assertEquals(false, house.getSold());
		Assert.assertEquals(0, house.getNumberOfRooms());
		Assert.assertEquals(false, house.getHasGarden());
		Assert.assertEquals(false, house.getHasGarage());
		
		house.setAddress(testSetAddress);
		house.setPrice(1.0f);
		house.setSold(true);
		house.setNumberOfRooms(1);
		house.setHasGarden(true);
		house.setHasGarage(true);
		
		Assert.assertEquals(testSetAddress, house.getAddress());
		Assert.assertEquals(1.0f, house.getPrice(), 0.001f);
		Assert.assertEquals(true, house.getSold());
		Assert.assertEquals(1, house.getNumberOfRooms());
		Assert.assertEquals(true, house.getHasGarden());
		Assert.assertEquals(true, house.getHasGarage());
	}
	
	@Test
	void FlatTestGetAndSetValues() {
		Property house = getHouse();
		
		Assert.assertEquals(testAddress, house.getAddress());
		Assert.assertEquals(0.0f, house.getPrice(), 0.05f);
		Assert.assertEquals(false, house.getSold());
		Assert.assertEquals(0, house.getNumberOfRooms());
		Assert.assertEquals(0, house.getFloorNumber());
		Assert.assertEquals(0.0f, house.getMonthlyCharge(), 0.05f);
		
		house.setAddress(testSetAddress);
		house.setPrice(1.0f);
		house.setSold(true);
		house.setNumberOfRooms(1);
		house.setFloorNumber(1);
		house.setMonthlyCharge(1.0f);
		
		Assert.assertEquals(testSetAddress, house.getAddress());
		Assert.assertEquals(1.0f, house.getPrice(), 0.05f);
		Assert.assertEquals(true, house.getSold());
		Assert.assertEquals(1, house.getNumberOfRooms());
		Assert.assertEquals(1, house.getFloorNumber());
		Assert.assertEquals(1.0f, house.getMonthlyCharge(), 0.05f);
	}

	static Admin getAdmin() {
		return new Admin(testName, testUsername, testPassword);
	}
	
	static Branch getBranch() {
		return new Branch(testName, testAddress, testPhoneNumber, testEmailAddress, testWebAddress, testUsername, testPassword);
	}
	
	static Property getHouse() {
		return new Property(testAddress, 0.0f, false, 0, false, false);
	}
	
	static Property getFlat() {
		return new Property(testAddress, 0.0f, false, 0, 0, 0.0f);
	}
}
