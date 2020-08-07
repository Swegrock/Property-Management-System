package com.mootz.property.Views;

import com.mootz.property.Managers.*;
import com.mootz.property.Models.*;

public class Main {
	public static void main(String[] args) {
		Branch branch = new Branch("Name", "Add", "Num", "@", ".com", "admin", "pass");
		branch.addProperty(new Property("Add1", 0.0f, false, 0, false, false));
		branch.addProperty(new Property("Add2", 0.0f, false, 0, false, false));
		System.out.println(LoginManager.CreateLogin(branch));
		Branch b2 = (Branch)LoginManager.CanLogin("admin", "pass");
		System.out.println(b2.getAllProperties().size());
	}
}