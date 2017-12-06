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

@Entity
@Table(name = "vehicles")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String brand;
	
	@NotBlank
	private String model;
	
	@NotBlank
	private String colour;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateOfFirstRegistration;
	
	@NotBlank
	private String placeOfFirstRegistration;

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
