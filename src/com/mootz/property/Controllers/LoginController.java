package com.mootz.property.Controllers;

import com.mootz.property.Views.*;
import com.mootz.property.Managers.*;
import com.mootz.property.Models.*;

public class LoginController {
	private LoginView loginView;
	
	public LoginController(LoginView loginView) {
		this.loginView = loginView;
	}
	
	public boolean Login(String username, String password) {
		IAccount account = LoginManager.CanLogin(username, password);
		if (account instanceof Admin) {
			AdminController adminController = new AdminController((Admin)account);
			AdminView adminView = new AdminView(adminController);
			return true;
		} else if (account instanceof Branch) {
			BranchController branchController = new BranchController((Branch)account);
			BranchView branchView = new BranchView(branchController);
			return true;
		} else {
			return false;
		}
	}
}
