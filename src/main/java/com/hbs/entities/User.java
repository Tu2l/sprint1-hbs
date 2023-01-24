package com.hbs.entities;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@Column(name = "user_id", unique = true, nullable = true)
	private int userId;

	@Column(name = "user_name", nullable = true)
	private String userName;

	@Column(name = "email", unique = true, nullable = true)
	private String email;

	@Column(name = "password", unique = true, nullable = true)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = true)
	private UserRole role;

	@Column(name = "mobile", unique = true, nullable = true)
	private String mobile;

	@Column(name = "address", nullable = true)
	private String address;

}