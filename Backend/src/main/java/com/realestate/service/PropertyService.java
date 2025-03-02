package com.realestate.service;

import com.realestate.model.Property;
import com.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    // 🏡 Save a new property
    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    // 🔍 Get all properties
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // ✏️ Update an existing property
    public Property updateProperty(Long id, Property updatedProperty) {
        Optional<Property> existingProperty = propertyRepository.findById(id);

        if (existingProperty.isPresent()) {
            Property property = existingProperty.get();
            property.setTransactionType(updatedProperty.getTransactionType());
            property.setPropertyType(updatedProperty.getPropertyType());
            property.setPrice(updatedProperty.getPrice());
            property.setImage(updatedProperty.getImage());
            property.setLocation(updatedProperty.getLocation());
            


            return propertyRepository.save(property);
        } else {
            return null; // Handle Not Found case in Controller
        }
    }

    // 🗑️ Delete a property by ID
    public boolean deleteProperty(Long id) {
        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 🔘 Toggle Hide/Unhide a property
    public Property toggleHideProperty(Long id) {
        Optional<Property> existingProperty = propertyRepository.findById(id);

        if (existingProperty.isPresent()) {
            Property property = existingProperty.get();
            property.setHide(!property.isHide());
            return propertyRepository.save(property);
        }
        return null; // Handle Not Found case in Controller
    }
}
