package com.classicmodels.product.entities;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="products")
public class Product {

	@Id
	private String productCode;
	
	private String productName;
	
	@ManyToOne
	@JoinColumn(name="productLine")
	private ProductLine productLine;
	
	private String productScale; 
	
	private String productVendor;
	
	private String productDescription;
	
	private Integer quantityInStock;
	
	@Column(precision = 10, scale = 2) 
    private BigDecimal buyPrice;
	
	@Column(precision = 10, scale = 2) 
    private BigDecimal MSRP;

	@Override
	public int hashCode() {
		return Objects.hash(productCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productCode, other.productCode);
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", productLine=" + productLine
				+ ", productScale=" + productScale + ", productVendor=" + productVendor + ", productDescription="
				+ productDescription + ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + ", MSRP="
				+ MSRP + "]";
	}
	
	
	
}
