package com.classicmodels.employee.entities;

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
@Table(name="employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employeenumber")
	private Long employeeNumber;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(length=10)
	private String extension;
	
	private String email;
	
	@Column(name="jobtitle",length=50)
	private String jobTitle;
	
	@ManyToOne
	@JoinColumn(name="reportsto")
	private Employee reportsTo;
	
	@ManyToOne
	@JoinColumn(name="officecode")
	private Office officeCode;

	@Override
	public int hashCode() {
		return Objects.hash(employeeNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(employeeNumber, other.employeeNumber);
	}

	@Override
	public String toString() {
		return "Employee [employeeNumber=" + employeeNumber + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", extension=" + extension + ", email=" + email + ", jobTitle=" + jobTitle + ", reportsTo="
				+ reportsTo + ", officeCode=" + officeCode + "]";
	}

	public Employee(String firstName2, String lastName2, String email2, String jobTitle2, String extension2,
			Employee reportsTo2, Office officeCode2) {
	}
	
	
	
}
