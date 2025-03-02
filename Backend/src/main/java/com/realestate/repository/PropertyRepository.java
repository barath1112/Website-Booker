package com.realestate.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
	 
}