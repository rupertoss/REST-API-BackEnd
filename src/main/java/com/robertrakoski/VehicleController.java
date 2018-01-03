package com.robertrakoski;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robertrakoski.model.Vehicle;
import com.robertrakoski.repository.VehicleRepository;

/**
 * Controller class to control REST operations.
 * It supports GET all vehicles, GET specific vehicle, POST, PATCH and DELETE vehicle operations. 
 * Main root is: "/api".
 * @author rupertoss
 *
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class VehicleController {
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	/**
	 * Returns list of all vehicles in {@code VehicleRepository}
	 * 
	 * @return list of all vehicles in {@code VehicleRepository}
	 */
	@GetMapping("/vehicles")
	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}
	
	/**
	 * Posts the {@code vehicle} to the database and returns the HTTP response
	 * 
	 * @param vehicle the object of type vehicle to post to database
	 * @return HTTP OK response with body containing the vehicle
	 */
	@PostMapping("/vehicles")
	public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody Vehicle vehicle) {
		vehicleRepository.save(vehicle);
		return ResponseEntity.ok().body(vehicle);
	}
	
	/**
	 * Returns the HTTP response of vehicle with specific Id
	 * 
	 * @param vehicleId the long value representing vehicle Id
	 * @return HTTP OK response with body containing the vehicle with given {@code vehicleId}
	 */
	@GetMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable(value = "id") Long vehicleId) {
		Vehicle vehicle = vehicleRepository.findOne(vehicleId);
		if (vehicle == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(vehicle);
	}
	
	/**
	 * Patches the vehicle with {@code vehicleId} with given details {@code vehicleDetails}
	 * and returns HTTP response of vehicle with specific Id
	 * 
	 * @param vehicleId	the long value representing vehicle Id
	 * @param vehicleDetails the object of type vehicle to patch to database
	 * @return HTTP OK response with body containing the updated vehicle {@code vehicleDetails}
	 */
	@PatchMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable(value = "id") Long vehicleId, @Valid @RequestBody Vehicle vehicleDetails) {
		Vehicle vehicle = vehicleRepository.findOne(vehicleId);
		if(vehicle == null) {
			return ResponseEntity.notFound().build();
		}
		
		vehicle.setBrand(vehicleDetails.getBrand());
		vehicle.setModel(vehicleDetails.getModel());
		vehicle.setColour(vehicleDetails.getColour());
		vehicle.setDateOfFirstRegistration(vehicleDetails.getDateOfFirstRegistration());
		vehicle.setPlaceOfFirstRegistration(vehicleDetails.getPlaceOfFirstRegistration());
		
		Vehicle updatedVehicle = vehicleRepository.save(vehicle);
		
		return ResponseEntity.ok(updatedVehicle);
	}
	
	/**
	 * Deletes the vehicle with {@code vehicleId} and returns HTTP response of vehicle with specific Id
	 * 
	 * @param vehicleId the long value representing vehicle Id
	 * @return HTTP OK response with body containing the deleted vehicle
	 */
	@DeleteMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> deleteVehicle(@PathVariable(value = "id") Long vehicleId) {
		Vehicle vehicle = vehicleRepository.findOne(vehicleId);
		if(vehicle == null) {
			return ResponseEntity.notFound().build();
		}
		vehicleRepository.delete(vehicle);
		return ResponseEntity.ok().body(vehicle);
	}
	
}
