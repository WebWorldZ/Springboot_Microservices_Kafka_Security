package com.classicmodels.customer.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String checkNumber;
	
	@Column(precision = 10, scale = 2) 
    private BigDecimal amount;
	
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	
	@ManyToOne
	@JoinColumn(name="customernumber")
	private Customer customer;

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", checkNumber=" + checkNumber + ", amount=" + amount + ", paymentDate="
				+ paymentDate + ", customer=" + customer + "]";
	}

	
}
