package com.mootz.property.Controllers;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.mootz.property.Managers.DialogManager;
import com.mootz.property.Managers.LoginManager;
import com.mootz.property.Models.Branch;
import com.mootz.property.Models.Property;
import com.mootz.property.Views.BranchView;
import com.mootz.property.Views.ParseHelper;

public class BranchController {
	
	private LoginController loginController;
	
	private DefaultTableModel branchTable;
	
	private String cols[] = {"Property Type", "Address", "Number Of Rooms", "Price", "Has Garden", "Has Garage", "Floor Number", "Monthly Charge"};
	
	private String types[] = {"House", "Flat"};
	private String yesno[] = {"Yes", "No"};
	
	private Branch branch;
	private BranchView branchView;
	
	private boolean showSold;
	private boolean showHouses;
	private boolean showFlats;
	
	public BranchController(Branch branch, LoginController loginController) {
		this.branch = branch;
		this.branchTable = new DefaultTableModel(cols, 0);
		this.branchView = new BranchView(this);
		this.loginController = loginController;
		RefreshTable();
	}
	
	public DefaultTableModel getBranchTable() {
		return branchTable;
	}
	
	public void setShowSold(boolean showSold) {
		this.showSold = showSold;
		RefreshTable();
	}
	
	public void setShowHouses(boolean showHouses) {
		this.showHouses = showHouses;
		RefreshTable();
	}
	
	public void setShowFlats(boolean showFlats) {
		this.showFlats = showFlats;
		RefreshTable();
	}
	
	private Property GetNewProperty(String address, float price, Boolean sold, int numberOfRooms, Boolean hasGarden, Boolean hasGarage, int floorNumber, float monthlyCharge) {
		int index = DialogManager.GetDropDown("Select the type of property:", types);
		if (index < 0) {
			return null;
		}
		
		address = DialogManager.GetInput("Enter the property address:", address);
		if (address.equals(""))
			return null;
		
		price = GetFloatInput("Enter the price of the property:", Float.toString(price));
		if (price < 0)
			return null;
		
		sold = GetYesNoInput("Is the property sold?:");
		if (sold == null)
			return null;
		
		numberOfRooms = GetIntInput("Enter the number of rooms:", Integer.toString(numberOfRooms));
		if (numberOfRooms < 0)
			return null;
		
		if (index == 0) {
			hasGarden = GetYesNoInput("Does the property have a garden?:");
			if (hasGarden == null)
				return null;
			
			hasGarage = GetYesNoInput("Does the property have a garage?:");
			if (hasGarage == null)
				return null;
			
			return new Property(address, price, sold, numberOfRooms, hasGarden, hasGarage);
		} else {
			floorNumber = GetIntInput("Enter the number of floors:", Integer.toString(floorNumber));
			if (floorNumber < 0)
				return null;
			
			monthlyCharge = GetFloatInput("Enter the monthly charge:", Float.toString(monthlyCharge));
			if (monthlyCharge < 0)
				return null;
			return new Property(address, price, sold, numberOfRooms, floorNumber, monthlyCharge);
		}
	}
	
	private int GetIntInput(String contents, String defaultText) {
		String str = DialogManager.GetInput(contents, defaultText);
		if (str.equals(""))
			return -1;
		while (!ParseHelper.TryParseInt(str)) {
			str = DialogManager.GetInput("Invalid format. " + contents, defaultText);
		}
		return Integer.parseInt(str);
	}
	
	private float GetFloatInput(String contents, String defaultText) {
		String str = DialogManager.GetInput(contents, defaultText);
		if (str.equals(""))
			return -1;
		while (!ParseHelper.TryParseFloat(str)) {
			str = DialogManager.GetInput("Invalid format. " + contents, defaultText);
		}
		return Float.parseFloat(str);
	}
	
	private Boolean GetYesNoInput(String contents) {
		int num = DialogManager.GetDropDown(contents, yesno);
		if (num == 0) {
			return true;
		} else if (num == 1) {
			return false;
		} else {
			return null;
		}
	}
	
	public void AddNewProperty() {
		Property property = GetNewProperty("", 0, null, 0, null, null, 0, 0);
		if (property == null)
			return;
		branch.addProperty(property);
		
		RefreshTable();
		LoginManager.UpdateLogin(branch);
		DialogManager.DisplayMessage("New Property Added", "A new property has been successfully added to the list of branches.");
	}
	
	public void EditProperty(int index) {
		Property oldProperty = branch.getProperty(index);
		Property editedProperty = GetNewProperty(oldProperty.getAddress(), oldProperty.getPrice(), oldProperty.getSold(), oldProperty.getNumberOfRooms(), oldProperty.getHasGarden(), oldProperty.getHasGarage(), oldProperty.getFloorNumber(), oldProperty.getMonthlyCharge());
		if (editedProperty == null)
			return;
		branch.editProperty(editedProperty, index);
		
		RefreshTable();
		LoginManager.UpdateLogin(branch);
		DialogManager.DisplayMessage("Property Updated", Integer.toString(index));
	}
	
	public void DeleteProperty(int index) {
		Property property = branch.getProperty(index);
		branch.removeProperty(property);
		
		RefreshTable();
		LoginManager.UpdateLogin(branch);
		DialogManager.DisplayMessage("Property Deleted", Integer.toString(index));
	}
	
	public void ShowBranchWindow() {
		this.branchView.setVisible(true);
	}
	
	public void LogOut() {
		this.branchView.setVisible(false);
		this.loginController.ShowLoginWindow();
	}
	
	private void RefreshTable() {
		cols[3] = showSold ? "Sold Price" : "Price";
		branchTable.setColumnIdentifiers(cols);
		
		if (branchTable.getRowCount() > 0) {
		    for (int i = branchTable.getRowCount() - 1; i > -1; i--) {
		        branchTable.removeRow(i);
		    }
		}
		ArrayList<Property> properties;
		if (showHouses & showFlats) {
			properties = branch.getProperties(showSold);
		} else if (showHouses) {
			properties = branch.getHouses(showSold);
		} else if (showFlats) {
			properties = branch.getFlats(showSold);
		} else {
			properties = branch.getProperties(showSold);
		}
		
		for (Property property : properties) {
			String row[] = {property.getPropertyType().toString(), property.getAddress(), Integer.toString(property.getNumberOfRooms()), Float.toString(property.getPrice()), property.getHasGarden() ? "Yes" : "No", property.getHasGarage() ? "Yes" : "No", Integer.toString(property.getFloorNumber()), Float.toString(property.getMonthlyCharge())};
			branchTable.addRow(row);
		}
	}
	
	public void RefreshTable(String input) {
		cols[3] = showSold ? "Sold Price" : "Price";
		branchTable.setColumnIdentifiers(cols);
		
		if (branchTable.getRowCount() > 0) {
		    for (int i = branchTable.getRowCount() - 1; i > -1; i--) {
		        branchTable.removeRow(i);
		    }
		}
		ArrayList<Property> properties;
		if (showHouses & showFlats) {
			properties = branch.getProperties(input, showSold);
		} else if (showHouses) {
			properties = branch.getHouses(input, showSold);
		} else if (showFlats) {
			properties = branch.getFlats(input, showSold);
		} else {
			properties = branch.getProperties(input, showSold);
		}
		
		for (Property property : properties) {
			String row[] = {property.getPropertyType().toString(), property.getAddress(), Integer.toString(property.getNumberOfRooms()), Float.toString(property.getPrice()), property.getHasGarden() ? "Yes" : "No", property.getHasGarage() ? "Yes" : "No", Integer.toString(property.getFloorNumber()), Float.toString(property.getMonthlyCharge())};
			branchTable.addRow(row);
		}
	}
}