package com.mootz.property.Controllers;

import com.mootz.property.Views.*;
import com.mootz.property.Managers.*;
import com.mootz.property.Models.*;

public class LoginController {
	
	// The login view.
	private LoginView loginView;
	
	public LoginController() {
		this.loginView = new LoginView(this);
	}
	
	// Logs in the user to the correct view assuming the username and password match an admin or branch supervisor.
	public void Login(String username, String password) {
		IAccount account = LoginManager.CanLogin(username, password);
		if (account instanceof Admin) {
			AdminController adminController = new AdminController((Admin)account, this);
			loginView.setVisible(false);
			adminController.ShowAdminWindow();
		} else if (account instanceof Branch) {
			BranchController branchController = new BranchController((Branch)account, this);
			loginView.setVisible(false);
			branchController.ShowBranchWindow();
		} else {
			DialogManager.DisplayMessage("Incorrect details", "The username or password entered were not recognised.");
		}
	}
	
	// Shows the login view.
	public void ShowLoginWindow() {
		this.loginView.setVisible(true);
	}
}
