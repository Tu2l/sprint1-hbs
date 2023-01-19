package com.hbs.service;

import com.hbs.entities.Admin;

public interface IAdminService {
	public Admin signIn(Admin admin);
	public Admin signOut(Admin admin);
	
}
