package com.mootz.property.Views;

import com.mootz.property.Managers.*;
import com.mootz.property.Controllers.*;
import com.mootz.property.Models.*;
import com.mootz.property.Views.*;

public class Main {
	public static void main(String[] args) {
		Admin admin = new Admin("Main Administrator", "admin", "12345");
		LoginManager.CreateLogin(admin);		
		LoginController loginController = new LoginController();
		loginController.ShowLoginWindow();
	}
}