package com.mootz.property.Views;

import com.mootz.property.Managers.*;
import com.mootz.property.Controllers.*;
import com.mootz.property.Models.*;
import com.mootz.property.Views.*;

public class Main {
	public static void main(String[] args) {
		Admin admin = new Admin("Main Administrator", "admin", "12345");
		System.out.println(LoginManager.CreateLogin(admin));		
		LoginView loginView = new LoginView();
		loginView.setVisible(true);
	}
}