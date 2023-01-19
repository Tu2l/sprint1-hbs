package com.hbs.service;

import com.hbs.entities.Admin;

public interface IAdminService {
	Admin signIn(Admin admin);

	Admin signOut(Admin admin);
}
