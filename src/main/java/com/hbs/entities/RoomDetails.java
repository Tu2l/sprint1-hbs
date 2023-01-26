package com.hbs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "room_details")
public class RoomDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "room_id")
	private int roomId;

	@ManyToOne
	@JoinColumn(name = "fk_hotel_id")
	private Hotel hotel;

	@Column(name = "room_no", nullable = true)
	private String roomNo;

	@Column(name = "room_type", nullable = true)
	private String roomType;

	@Column(name = "rate_per_day", nullable = true)
	private double ratePerDay;

	@Column(name = "photo")
	private String imageUrl;

}