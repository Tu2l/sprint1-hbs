package com.hbs.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hbs.entities.Admin;
import com.hbs.exceptions.AdminAlreadyExistsException;
import com.hbs.exceptions.AdminNotFoundException;
import com.hbs.exceptions.InvalidCredentialsException;
import com.hbs.repository.AdminRepository;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
	
	private static final String  ADMIN_EXCEPTION = "Admin not found";
	
	@Mock
	private AdminRepository adminRepository;
	
	@InjectMocks
	private AdminServiceImpl adminService;
	
	
	 public void testSignIn_validCredentials_returnsAdmin() throws AdminNotFoundException, InvalidCredentialsException {
	        Admin admin = new Admin(0, "admin", "admin@example.com", "password");
	        when(adminRepository.findByEmail("admin@example.com")).thenReturn(admin);
	        Admin result = adminService.signIn(admin);
	        assertEquals(admin, result);
	    }

	    @Test
	    public void testSignIn() {
	        Admin admin = new Admin(0, "admin", "admin@example.com", "password");
	        when(adminRepository.findByEmail("admin@example.com")).thenReturn(null);
	        assertThrows(AdminNotFoundException.class, () -> adminService.signIn(admin));
	    }

	    @Test
	    public void testSignOut() {
	        Admin admin = new Admin(0, "admin", "admin@example.com", "password");
	        Admin result = adminService.signOut(admin);
	        assertEquals(admin, result);
	    }
 
	    @Test
	    public void testAdd() throws AdminAlreadyExistsException {
	        Admin admin = new Admin(0, "admin", "admin@example.com", "password");
	        when(adminRepository.findByEmail("admin@example.com")).thenReturn(null);
	        when(adminRepository.save(admin)).thenReturn(admin);
	        Admin result = adminService.add(admin);
	        assertEquals(admin, result);
	    }

	

}
