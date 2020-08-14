package com.mootz.property.Managers;

import java.util.ArrayList;

import com.mootz.property.Models.*;

import java.io.File;

public class LoginManager {
	static String dataPathLocation = "Data";
	static String dataFileLocation = "Data/%s.dat";
	
	public static IAccount CanLogin(String username, String password) {
		IAccount loadedAccount = (IAccount)FileManager.LoadObjectFromFile(String.format(dataFileLocation, username));
		if (loadedAccount instanceof Branch || loadedAccount instanceof Admin) {
			if (password.equals(loadedAccount.getPassword())) {
				return loadedAccount;
			}
		}
		return null;
	}
	
	public static IAccount CanLogin(String username) {
		IAccount loadedAccount = (IAccount)FileManager.LoadObjectFromFile(String.format(dataFileLocation, username));
		if (loadedAccount instanceof Branch || loadedAccount instanceof Admin) {
			return loadedAccount;
		}
		return null;
	}
	
	public static boolean DoesAccountExist(String username) {
		Object loadedAccount = FileManager.LoadObjectFromFile(String.format(dataFileLocation, username));
		if (loadedAccount instanceof Branch) {
			return true;
		} else if (loadedAccount instanceof Admin) {
			return true;
		}
		return false;
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
	
	public static boolean DeleteLogin(String username) {
		String fileLocation = String.format(dataFileLocation, username);
		Object loadedAccount = FileManager.LoadObjectFromFile(fileLocation);
		if (loadedAccount instanceof Branch) {
			return FileManager.DeleteFile(fileLocation);
		}
		return false;
	}
	
	public static ArrayList<Branch> GetBranches() {
		ArrayList<Branch> branches = new ArrayList<Branch>();
		for (String file : FileManager.GetAllFiles(dataPathLocation)) {
			File f = new File(file);
			String fname = f.getName();
			int pos = fname.lastIndexOf(".");
			if (pos > 0) {
			    fname = fname.substring(0, pos);
			}
			IAccount account = CanLogin(fname);
			if (account instanceof Branch) {
				branches.add((Branch)account);
			}
		}
		return branches;
	}
}