package com.classicmodels.employee.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="offices")
public class Office {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer officecode;
	
	private String city;
	
	private String phone;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String state;
	
	private String country; 
	
	@Column(length=15)
	private String postalcode; 
	
	@Column(length=10)
	private String territory;

	@Override
	public int hashCode() {
		return Objects.hash(officecode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Office other = (Office) obj;
		return Objects.equals(officecode, other.officecode);
	}

	@Override
	public String toString() {
		return "Office [officecode=" + officecode + ", city=" + city + ", phone=" + phone + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", state=" + state + ", country=" + country
				+ ", postalcode=" + postalcode + ", territory=" + territory + "]";
	}

	public Office(String city2, String phone2, String addressLine12, String addressLine22, String state2,
			String country2, String postalcode2, String territory2) {
		
	} 
	
	
	
}
