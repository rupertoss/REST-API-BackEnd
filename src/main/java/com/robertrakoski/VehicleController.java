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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class VehicleController {
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	@GetMapping("/vehicles")
	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}
	
	@PostMapping("/vehicles")
	public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody Vehicle vehicle) {
		vehicleRepository.save(vehicle);
		return ResponseEntity.ok().body(vehicle);
	}
	
	@GetMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable(value = "id") Long vehicleId) {
		Vehicle vehicle = vehicleRepository.findOne(vehicleId);
		if (vehicle == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(vehicle);
	}
	
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
