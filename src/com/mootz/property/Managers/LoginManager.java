package com.mootz.property.Managers;

import com.mootz.property.Models.*;

public class LoginManager {
	static String dataFileLocation = "Data/%s.dat";
	
	public static IAccount CanLogin(String username, String password) {
		Object loadedAccount = FileManager.LoadObjectFromFile(String.format(dataFileLocation, username));
		if (loadedAccount instanceof Branch) {
			Branch branch = (Branch)loadedAccount;
			if (password.equals(branch.getPassword())) {
				return branch;
			}
		} else if (loadedAccount instanceof Admin) {
			Admin admin = (Admin)loadedAccount;
			if (password.equals(admin.getPassword())) {
				return admin;
			}
		}
		return null;
	}
	
	public static boolean CreateLogin(Branch branch) {
		String fileLocation = String.format(dataFileLocation, branch.getUsername());
		Object loadedAccount = FileManager.LoadObjectFromFile(fileLocation);
		if (loadedAccount == null) {
			return FileManager.WriteObjectToFile(branch, fileLocation);
		}
		return false;
	}
	
	public static boolean CreateLogin(Admin admin) {
		String fileLocation = String.format(dataFileLocation, admin.getUsername());
		Object loadedAccount = FileManager.LoadObjectFromFile(fileLocation);
		if (loadedAccount == null) {
			return FileManager.WriteObjectToFile(admin, fileLocation);
		}
		return false;
	}
	
	public static boolean UpdateLogin(Branch branch) {
		String fileLocation = String.format(dataFileLocation, branch.getUsername());
		Object loadedAccount = FileManager.LoadObjectFromFile(fileLocation);
		if (loadedAccount instanceof Branch) {
			return FileManager.WriteObjectToFile(branch, fileLocation);
		}
		return false;
	}
	
	public static boolean UpdateLogin(Admin admin) {
		String fileLocation = String.format(dataFileLocation, admin.getUsername());
		Object loadedAccount = FileManager.LoadObjectFromFile(fileLocation);
		if (loadedAccount instanceof Admin) {
			return FileManager.WriteObjectToFile(admin, fileLocation);
		}
		return false;
	}
}