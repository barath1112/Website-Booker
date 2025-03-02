package com.realestate.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userType;
    private String propertyType;
    private String transactionType;
    private String homeDetails;
    private double price;
    private String mobileNumber;
    private String notes;
    private String email;
    
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean hide;

    @Lob
    @Column(columnDefinition = "LONGTEXT") // ✅ Ensure long Base64 storage
    private String image;

    @Embedded
    private Location location;
    
    

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Property(Long id, String userType, String propertyType, String transactionType, String homeDetails,
			double price, String mobileNumber, String notes, String email, boolean hide, String image,
			Location location) {
		super();
		this.id = id;
		this.userType = userType;
		this.propertyType = propertyType;
		this.transactionType = transactionType;
		this.homeDetails = homeDetails;
		this.price = price;
		this.mobileNumber = mobileNumber;
		this.notes = notes;
		this.email = email;
		this.hide = hide;
		this.image = image;
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getHomeDetails() {
		return homeDetails;
	}

	public void setHomeDetails(String homeDetails) {
		this.homeDetails = homeDetails;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isHide() {
		return hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
    
    
    
}

