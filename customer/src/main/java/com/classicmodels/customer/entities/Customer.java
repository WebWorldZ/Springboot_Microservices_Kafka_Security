package com.classicmodels.customer.entities;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customernumber")
	private Long customerNumber;
	
	private String customerName;
	
	private String contactLastName;
	
	private String contactFirstName;
	
	private String phone;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String city;
	
	private String state;
	
	@Column(name="postalCode",length=15)
	private String postalCode;
	
	private String country;
	
	@Column(precision = 10, scale = 2) 
    private BigDecimal creditlimit;
	
	@ManyToOne
	@JoinColumn(name="salesrepemployeenumber")
	private Employee salesRepEmployeeNumber;

	@Override
	public int hashCode() {
		return Objects.hash(customerNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(customerNumber, other.customerNumber);
	}

	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", customerName=" + customerName + ", contactLastName="
				+ contactLastName + ", contactFirstName=" + contactFirstName + ", phone=" + phone + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", country=" + country + ", creditlimit=" + creditlimit
				+ ", salesRepEmployeeNumber=" + salesRepEmployeeNumber + "]";
	}
	

	public Customer(String customerName2, String contactFirstName2, String contactLastName2, String phone2,
			String addressLine12, String addressLine22, String city2, String state2, String postalCode2,
			String country2, BigDecimal creditlimit2, Long salesRepEmployeeNumber2) {
	}

	
	
	
	
}
