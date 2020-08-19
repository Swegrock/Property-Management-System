package com.mootz.property.Controllers;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.mootz.property.Managers.DialogManager;
import com.mootz.property.Managers.LoginManager;
import com.mootz.property.Models.Branch;
import com.mootz.property.Models.Property;
import com.mootz.property.Models.PropertyType;
import com.mootz.property.Views.BranchView;
import com.mootz.property.Views.ParseHelper;

public class BranchController {
	
	// The main login controller, stored to reactivate the main login when logging out.
	private LoginController loginController;
	
	// The table of the branch view.
	private DefaultTableModel propertyTable;
	
	// The column titles of the table.
	private String cols[] = {"Property Type", "Address", "Number Of Rooms", "Price", "Has Garden", "Has Garage", "Floor Number", "Monthly Charge", "Sold"};
	
	// The combo box options for determining whether a property is a house or flat.
	private String types[] = {"House", "Flat"};
	// The combo box options for determining whether a yes no question.
	private String yesno[] = {"Yes", "No"};
	
	// The branch object.
	private Branch branch;
	// The branch view.
	private BranchView branchView;
	
	// Should sold properties be shown in the view.
	private boolean showSold;
	// Should houses be shown in the view.
	private boolean showHouses;
	// Should flats be shown in the view.
	private boolean showFlats;
	
	public BranchController(Branch branch, LoginController loginController) {
		this.branch = branch;
		this.propertyTable = new DefaultTableModel(cols, 0);
		this.branchView = new BranchView(this);
		this.loginController = loginController;
		RefreshTable();
	}
	
	// Gets the property table.
	public DefaultTableModel getPropertyTable() {
		return propertyTable;
	}
	
	// Sets show sold to display or not display sold properties.
	public void setShowSold(boolean showSold) {
		this.showSold = showSold;
		RefreshTable();
	}
	
	// Sets show sold to display or not display houses.
	public void setShowHouses(boolean showHouses) {
		this.showHouses = showHouses;
		RefreshTable();
	}
	
	// Sets show sold to display or not display flats.
	public void setShowFlats(boolean showFlats) {
		this.showFlats = showFlats;
		RefreshTable();
	}
	
	// Creates a property object by asking the user for all of the details of the property.
	// Then once all the details have been provided the object will be created and returned.
	// If at any point the user neglects to provide any details null will be returned.
	private Property GetNewProperty(PropertyType type, String address, float price, Boolean sold, int numberOfRooms, Boolean hasGarden, Boolean hasGarage, int floorNumber, float monthlyCharge) {
		int index = DialogManager.GetDropDown("Select the type of property:", types, (type == PropertyType.House) ? 0 : 1);
		if (index < 0) {
			return null;
		}
		
		address = DialogManager.GetInput("Enter the property address:", address);
		if (address == null || address.equals(""))
			return null;
		
		price = GetFloatInput("Enter the price of the property:", Float.toString(price));
		if (price < 0)
			return null;
		
		sold = GetYesNoInput("Is the property sold?:", sold);
		if (sold == null)
			return null;
		
		numberOfRooms = GetIntInput("Enter the number of rooms:", Integer.toString(numberOfRooms));
		if (numberOfRooms < 0)
			return null;
		
		if (index == 0) {
			hasGarden = GetYesNoInput("Does the property have a garden?:", hasGarden);
			if (hasGarden == null)
				return null;
			
			hasGarage = GetYesNoInput("Does the property have a garage?:", hasGarden);
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
	
	// Gets an integer input from the user.
	private int GetIntInput(String contents, String defaultText) {
		String str = DialogManager.GetInput(contents, defaultText);
		if (str == null || str.equals(""))
			return -1;
		while (!ParseHelper.TryParseInt(str)) {
			str = DialogManager.GetInput("Invalid format. " + contents, defaultText);
		}
		return Integer.parseInt(str);
	}
	
	// Gets a floating point input from the user.
	private float GetFloatInput(String contents, String defaultText) {
		String str = DialogManager.GetInput(contents, defaultText);
		if (str == null || str.equals(""))
			return -1;
		while (!ParseHelper.TryParseFloat(str)) {
			str = DialogManager.GetInput("Invalid format. " + contents, defaultText);
		}
		return Float.parseFloat(str);
	}
	
	// Gets a yes or no (boolean) input from the user.
	private Boolean GetYesNoInput(String contents, Boolean defaultValue) {
		if (defaultValue == null)
			defaultValue = true;
		int oldnum = defaultValue ? 0 : 1;
		int num = DialogManager.GetDropDown(contents, yesno, oldnum);
		if (num == 0) {
			return true;
		} else if (num == 1) {
			return false;
		} else {
			return null;
		}
	}
	
	// Adds a new property.
	public void AddNewProperty() {
		Property property = GetNewProperty(PropertyType.House ,"", 0, null, 0, null, null, 0, 0);
		if (property == null)
			return;
		branch.addProperty(property);
		
		RefreshTable();
		LoginManager.UpdateLogin(branch);
		DialogManager.DisplayMessage("New Property Added", "A new property has been successfully added to the list of properties.");
	}
	
	// Edits the currently selected property in the branch view.
	public void EditProperty(int index) {
		Property oldProperty = branch.getProperty(propertyTable.getValueAt(index, 1).toString());
		Property editedProperty = GetNewProperty(oldProperty.getPropertyType(), oldProperty.getAddress(), oldProperty.getPrice(), oldProperty.getSold(), oldProperty.getNumberOfRooms(), oldProperty.getHasGarden(), oldProperty.getHasGarage(), oldProperty.getFloorNumber(), oldProperty.getMonthlyCharge());
		if (editedProperty == null)
			return;
		branch.removeProperty(oldProperty);
		branch.addProperty(editedProperty);
		
		RefreshTable();
		LoginManager.UpdateLogin(branch);
		DialogManager.DisplayMessage("Property Updated", "The property has been successfully updated.");
	}

	// Deletes the currently selected property in the branch view.
	public void DeleteProperty(int index) {
		Property property = branch.getProperty(propertyTable.getValueAt(index, 1).toString());
		branch.removeProperty(property);
		
		RefreshTable();
		LoginManager.UpdateLogin(branch);
		DialogManager.DisplayMessage("Property Deleted", "The property has been successfully deleted.");
	}
	
	// Shows the branch view.
	public void ShowBranchWindow() {
		this.branchView.setVisible(true);
	}
	
	// Logs out the branch account and reopens the login window.
	public void LogOut() {
		this.branchView.setVisible(false);
		this.loginController.ShowLoginWindow();
	}

	// Refreshes the table by clearing it.
	// Then to repopulate the table it determines what should be displayed according to the selected options.
	private void RefreshTable() {
		cols[3] = showSold ? "Sold Price" : "Price";
		propertyTable.setColumnIdentifiers(cols);
		
		if (propertyTable.getRowCount() > 0) {
		    for (int i = propertyTable.getRowCount() - 1; i > -1; i--) {
		    	propertyTable.removeRow(i);
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
			properties = branch.getProperties();
		}
		
		for (Property property : properties) {
			String hasGarden;
			String hasGarage;
			String floorNumber;
			String monthlyCharge;
			if (property.getPropertyType() == PropertyType.House) {
				hasGarden = property.getHasGarden() ? "Yes" : "No";
				hasGarage = property.getHasGarage() ? "Yes" : "No";
				floorNumber = "N/A";
				monthlyCharge = "N/A";
			} else {
				hasGarden = "N/A";
				hasGarage = "N/A";
				floorNumber = Integer.toString(property.getFloorNumber());
				monthlyCharge = Float.toString(property.getMonthlyCharge());
			}
			String row[] = {property.getPropertyType().toString(), property.getAddress(), Integer.toString(property.getNumberOfRooms()), Float.toString(property.getPrice()), hasGarden, hasGarage, floorNumber, monthlyCharge, property.getSold() ? "Yes" : "No"};
			propertyTable.addRow(row);
		}
	}
	
	// Refreshes the table by clearing it.
	// Then to repopulate the table it determines what should be displayed according to the selected options.
	// It also uses the input to select certain properties.
	public void RefreshTable(String input) {
		cols[3] = showSold ? "Sold Price" : "Price";
		propertyTable.setColumnIdentifiers(cols);
		
		if (propertyTable.getRowCount() > 0) {
		    for (int i = propertyTable.getRowCount() - 1; i > -1; i--) {
		    	propertyTable.removeRow(i);
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
			properties = branch.getProperties(input);
		}
		
		for (Property property : properties) {
			String hasGarden;
			String hasGarage;
			String floorNumber;
			String monthlyCharge;
			if (property.getPropertyType() == PropertyType.House) {
				hasGarden = property.getHasGarden() ? "Yes" : "No";
				hasGarage = property.getHasGarage() ? "Yes" : "No";
				floorNumber = "N/A";
				monthlyCharge = "N/A";
			} else {
				hasGarden = "N/A";
				hasGarage = "N/A";
				floorNumber = Integer.toString(property.getFloorNumber());
				monthlyCharge = Float.toString(property.getMonthlyCharge());
			}
			String row[] = {property.getPropertyType().toString(), property.getAddress(), Integer.toString(property.getNumberOfRooms()), Float.toString(property.getPrice()), hasGarden, hasGarage, floorNumber, monthlyCharge, property.getSold() ? "Yes" : "No"};
			propertyTable.addRow(row);
		}
	}
}