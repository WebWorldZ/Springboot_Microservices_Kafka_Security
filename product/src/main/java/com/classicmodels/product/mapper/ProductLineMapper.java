package com.classicmodels.product.mapper;

import org.springframework.stereotype.Service;

import com.classicmodels.product.dto.ProductLineDTO;
import com.classicmodels.product.entities.ProductLine;

@Service
public class ProductLineMapper {

	public ProductLineDTO mapToProductLineDTO(ProductLine productLine) {
		ProductLineDTO productLineDTO = new ProductLineDTO(
				productLine.getProductLine(),
				productLine.getTextDescription(),
				productLine.getHtmlDescription(),
				productLine.getImage()
				);
		return productLineDTO;
	}
	
	public ProductLine mapToProductLine(ProductLineDTO dto) {
		ProductLine productLine = new ProductLine();
		productLine.setProductLine(dto.productLine());
		productLine.setTextDescription(dto.textDescription());
		productLine.setHtmlDescription(dto.htmlDescription());
		productLine.setImage(dto.image());
		return productLine; 
	}
}
