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
@Table(name = "hotels")
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "hotel_id")
	private int hotelId;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "hotel_name", nullable = false)
	private String hotelName;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "avg_rate_per_day", nullable = false)
	private double avgRatePerDay;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "phone1", unique = true, nullable = false)
	private String phone1;

	@Column(name = "phone2", unique = true, nullable = false)
	private String phone2;

	@Column(name = "website", unique = true, nullable = false)
	private String website;
}
