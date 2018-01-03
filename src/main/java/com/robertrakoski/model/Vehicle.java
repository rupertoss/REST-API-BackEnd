package com.robertrakoski.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * The class representing vehicles. 
 * It stores data like: id, brand, model, colour,
 * date of first registration, place of first registration.
 * 
 * Contains Hibernate annotations for database.
 * @author rupertoss
 *
 */
@Entity
@Table(name = "vehicles")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;		//vehicle id - Primary Key
	
	@NotBlank
	private String brand;	//vehicle brand - cannot be null
	
	@NotBlank
	private String model;	//vehicle model - cannot be null
	
	@NotBlank
	private String colour;	//vehicle colour - cannot be null
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateOfFirstRegistration;	//vehicle date of first registration - cannot be null
	
	@NotBlank
	private String placeOfFirstRegistration; //vehicle place of first registration - cannot be null

	public Long getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Date getDateOfFirstRegistration() {
		return dateOfFirstRegistration;
	}

	public void setDateOfFirstRegistration(Date dateOfFirstRegistration) {
		this.dateOfFirstRegistration = dateOfFirstRegistration;
	}

	public String getPlaceOfFirstRegistration() {
		return placeOfFirstRegistration;
	}

	public void setPlaceOfFirstRegistration(String placeOfFirstRegistration) {
		this.placeOfFirstRegistration = placeOfFirstRegistration;
	}

	
}
