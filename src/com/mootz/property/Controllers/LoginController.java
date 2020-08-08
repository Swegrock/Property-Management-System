package com.mootz.property.Controllers;

import com.mootz.property.Views.*;
import com.mootz.property.Managers.*;
import com.mootz.property.Models.*;

public class LoginController {
	private LoginView loginView;
	
	public LoginController(LoginView loginView) {
		this.loginView = loginView;
	}
	
	public void Login(String username, String password) {
		IAccount account = LoginManager.CanLogin(username, password);
		if (account instanceof Admin) {
			AdminController adminController = new AdminController((Admin)account);
			AdminView adminView = new AdminView(adminController);
			loginView.setVisible(false);
		} else if (account instanceof Branch) {
			BranchController branchController = new BranchController((Branch)account);
			BranchView branchView = new BranchView(branchController);
			loginView.setVisible(false);
		} else {
			loginView.DisplayDialog("Incorrect details", "The username or password entered were not recognised.");
		}
	}
}
