package com.realestate.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.realestate.model.Property;
import com.realestate.repository.PropertyRepository;
import com.realestate.service.PropertyService;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "http://localhost:5173")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    
    @Autowired
    private PropertyRepository propertyRepository;

    // 🏡 Add Property
    @PostMapping
    public ResponseEntity<Property> registerProperty(@RequestBody Property property) {
        System.out.println("Received Property Data: " + property);
        System.out.println("Received Image Length: " + (property.getImage() != null ? property.getImage().length() : "No Image"));

        Property savedProperty = propertyService.saveProperty(property);
        return ResponseEntity.ok(savedProperty);
    }

    // 🔍 Get All Properties
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // ✏️ Update Property by ID
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property updatedProperty) {
        Optional<Property> existingProperty = propertyRepository.findById(id);

        if (existingProperty.isPresent()) {
            Property property = existingProperty.get();
            property.setTransactionType(updatedProperty.getTransactionType());
            property.setPropertyType(updatedProperty.getPropertyType());
            property.setPrice(updatedProperty.getPrice());
            property.setImage(updatedProperty.getImage()); // Update Image if needed
            property.setLocation(updatedProperty.getLocation());


            Property savedProperty = propertyRepository.save(property);
            return ResponseEntity.ok(savedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🗑️ Delete Property by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

 // 🔍 Get Property by ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Optional<Property> property = propertyRepository.findById(id);
        return property.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔘 Toggle Hide/Unhide Property by ID
    @PatchMapping("/{id}")
    public ResponseEntity<Property> toggleHideProperty(@PathVariable Long id) {
        Optional<Property> existingProperty = propertyRepository.findById(id);

        if (existingProperty.isPresent()) {
            Property property = existingProperty.get();
            property.setHide(!property.isHide()); // Toggle Hide Status
            Property updatedProperty = propertyRepository.save(property);
            return ResponseEntity.ok(updatedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
