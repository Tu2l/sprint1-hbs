package com.hbs.entities;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;

import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "user_details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
<<<<<<< HEAD
	@Column(name = "user_id", unique = true, nullable = false)
=======
	@Column(name = "user_id", unique = true, nullable = true)
>>>>>>> branch 'main' of https://github.com/Tu2l/sprint1-hbs.git
	private int userId;

<<<<<<< HEAD
	@Column(name = "user_name", nullable = false)
=======
	@Column(name = "user_name", nullable = true)
>>>>>>> branch 'main' of https://github.com/Tu2l/sprint1-hbs.git
	private String userName;

<<<<<<< HEAD
	@Column(name = "email", unique = true, nullable = false)
=======
	@Column(name = "email", unique = true, nullable = true)
>>>>>>> branch 'main' of https://github.com/Tu2l/sprint1-hbs.git
	private String email;

<<<<<<< HEAD
	@Column(name = "password", unique = true, nullable = false)
=======
	@Column(name = "password", unique = true, nullable = true)
>>>>>>> branch 'main' of https://github.com/Tu2l/sprint1-hbs.git
	private String password;

	@Column(name = "role", nullable = true)
	private String role;

<<<<<<< HEAD
	@Column(name = "mobile", unique = true, nullable = false)
=======
	@Column(name = "mobile", unique = true, nullable = true)
>>>>>>> branch 'main' of https://github.com/Tu2l/sprint1-hbs.git
	private String mobile;

<<<<<<< HEAD
	@Column(name = "address", nullable = false)
=======
	@Column(name = "address", nullable = true)
>>>>>>> branch 'main' of https://github.com/Tu2l/sprint1-hbs.git
	private String address;

}