package com.mootz.property.Views;

import com.mootz.property.Managers.*;
import com.mootz.property.Controllers.*;
import com.mootz.property.Models.*;
import com.mootz.property.Views.*;

public class Main {
	public static void main(String[] args) {
		// Create the default administrator profile, which is required to exist
		Admin admin = new Admin("Main Administrator", "admin", "12345");
		LoginManager.CreateLogin(admin);
		// Create the login controller and show the view.
		LoginController loginController = new LoginController();
		loginController.ShowLoginWindow();
	}
}