package com.classicmodels.customer.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
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
@Table(name="employeesbackup")
public class Employee {
	
	@Id
	private Long employeeNumber;		
	private String firstName;		
	private String lastName;		
	private String email;		
	private String jobTitle;		
	private String extension;
	
	@Override
	public String toString() {
		return "Employee [employeeNumber=" + employeeNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", jobTitle=" + jobTitle + ", extension=" + extension + "]";
	}

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

	
}
