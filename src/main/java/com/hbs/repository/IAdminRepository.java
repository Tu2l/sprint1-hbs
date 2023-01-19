package com.hbs.repository;

import com.hbs.entities.*;

public interface IAdminRepository {
	public Admin signIn(Admin admin);
	public Admin signOut(Admin admin);
	
}
