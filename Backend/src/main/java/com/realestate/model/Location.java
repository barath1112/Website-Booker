package com.realestate.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable  // ✅ Allows embedding in Property
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private String state;
    private String district;
    private String pincode;
    
    
    
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Location(String state, String district, String pincode) {
		super();
		this.state = state;
		this.district = district;
		this.pincode = pincode;
	}


	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
    
    
}
