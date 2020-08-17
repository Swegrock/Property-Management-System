package com.mootz.property.Controllers;

import javax.swing.table.DefaultTableModel;

import com.mootz.property.Managers.DialogManager;
import com.mootz.property.Managers.LoginManager;
import com.mootz.property.Models.Admin;
import com.mootz.property.Models.Branch;
import com.mootz.property.Views.AdminView;

public class AdminController {
	
	// The main login controller, stored to reactivate the main login when logging out.
	private LoginController loginController;
	
	// The table of the admin view.
	private DefaultTableModel branchTable;
	
	// The column titles of the table.
	private String cols[] = {"Name", "Address", "Phone Number", "Email Address", "Web Address", "Username", "Password"};
	
	// The admin object.
	private Admin admin;
	// The admin view.
	private AdminView adminView;
	
	public AdminController(Admin admin, LoginController loginController) {
		this.admin = admin;
		this.branchTable = new DefaultTableModel(cols, 0);
		this.adminView = new AdminView(this);
		this.loginController = loginController;
		RefreshTable();
	}
	
	public DefaultTableModel getBranchTable() {
		return branchTable;
	}
	
	// Creates a branch object by asking the user for all of the details of the branch.
	// Then once all the details have been provided the object will be created and returned.
	// If at any point the user neglects to provide any details null will be returned.
	public Branch GetNewBranch(String name, String address, String phoneNumber, String emailAddress, String webAddress, String username, String password) {
		name = DialogManager.GetInput("Enter the name of the branch:", name);
		if (name.equals(""))
			return null;
		address = DialogManager.GetInput("Enter the branch address:", address);
		if (address.equals(""))
			return null;
		phoneNumber = DialogManager.GetInput("Enter the branch phone number:", phoneNumber);
		if (phoneNumber.equals(""))
			return null;
		emailAddress = DialogManager.GetInput("Enter the email address:", emailAddress);
		if (emailAddress.equals(""))
			return null;
		webAddress = DialogManager.GetInput("Enter the web address:", webAddress);
		if (webAddress.equals(""))
			return null;
		if (!username.equals("")) {
			String oldName = username;
			username = DialogManager.GetInput("Enter the username for the branch supervisor:", username);
			while (!oldName.equals(username) && LoginManager.DoesAccountExist(username)) {
				username = DialogManager.GetInput("Username already in use. Enter a new username for the branch supervisor:", oldName);
			}
		} else {
			username = DialogManager.GetInput("Enter the username for the branch supervisor:", username);
			while (LoginManager.DoesAccountExist(username)) {
				username = DialogManager.GetInput("Username already in use. Enter a new username for the branch supervisor:", "");
			}
		}
		if (username.equals(""))
			return null;
		password = DialogManager.GetInput("Enter the password for the branch supervisor:", password);
		if (password.equals(""))
			return null;
		
		return new Branch(name, address, phoneNumber, emailAddress, webAddress, username, password);
	}
	
	// Adds a new branch.
	public void AddNewBranch() {
		Branch branch = GetNewBranch("", "", "", "", "", "", "");
		if (branch == null)
			return;
		LoginManager.CreateLogin(branch);
		
		RefreshTable();
		DialogManager.DisplayMessage("New Branch Added", "A new branch has been successfully created and added to the list of branches.");
	}
	
	// Edits the currently selected branch in the admin view.
	public void EditBranch(int index) {
		if (index < 0)
			return;
		String name = (String)branchTable.getValueAt(index, 0);
		String address = (String)branchTable.getValueAt(index, 1);
		String phoneNumber = (String)branchTable.getValueAt(index, 2);
		String emailAddress = (String)branchTable.getValueAt(index, 3);
		String webAddress = (String)branchTable.getValueAt(index, 4);
		String username = (String)branchTable.getValueAt(index, 5);
		String password = (String)branchTable.getValueAt(index, 6);
		
		Branch branch = GetNewBranch(name, address, phoneNumber, emailAddress, webAddress, username, password);
		if (branch == null)
			return;
		LoginManager.UpdateLogin(branch);
		
		RefreshTable();
		DialogManager.DisplayMessage("Branch Updated", "Branch has been successfully updated and changes will apply immediately.");
	}
	
	// Deletes the currently selected branch in the admin view.
	public void DeleteBranch(int index) {
		if (index < 0)
			return;
		String username = (String)branchTable.getValueAt(index, 5);
		
		LoginManager.DeleteLogin(username);
		
		RefreshTable();
		DialogManager.DisplayMessage("Branch Deleted", "Branch has been successfully deleted.");
	}
	
	// Edits the details of the admin.
	public void EditAdminDetails() {
		admin.setName(DialogManager.GetInput("Enter the name of the admin:", admin.getName()));
		admin.setUsername(DialogManager.GetInput("Enter the username for the admin:", admin.getUsername()));
		admin.setPassword(DialogManager.GetInput("Enter the password for the admin:", admin.getPassword()));
		LoginManager.UpdateLogin(admin);
		DialogManager.DisplayMessage("Admin Updated", "The details for the admin acount have been successfully updated.");
	}
	
	// Shows the admin view.
	public void ShowAdminWindow() {
		this.adminView.setVisible(true);
	}
	
	// Logs out the admin and reopens the login window.
	public void LogOut() {
		this.adminView.setVisible(false);
		this.loginController.ShowLoginWindow();
	}
	
	// Refreshes the table by clearing it and adding all branch files which can be found.
	private void RefreshTable() {
		if (branchTable.getRowCount() > 0) {
		    for (int i = branchTable.getRowCount() - 1; i > -1; i--) {
		        branchTable.removeRow(i);
		    }
		}
		for (Branch branch : LoginManager.GetBranches()) {
			String row[] = {branch.getName(), branch.getAddress(), branch.getPhoneNumber(), branch.getEmailAddress(), branch.getWebAddress(), branch.getUsername(), branch.getPassword()};
			branchTable.addRow(row);
		}
	}
}
