package com.mootz.property.Models;

import java.io.*;

public class Admin implements Serializable, IAccount {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String username;
	private String password;
	
	//Creates a new admin.
	public Admin(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	//Sets the name of the admin.
	public void setName(String name) {
		this.name = name;
	}
	
	//Sets the username for the admin.
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	
	//Sets the password for the admin.
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	//Gets the name of the admin.
	public String getName() {
		return this.name;
	}
	
	//Gets the admin username.
	@Override
	public String getUsername() {
		return this.username;
	}
	
	//Gets the admin password.
	@Override
	public String getPassword() {
		return this.password;
	}
}
