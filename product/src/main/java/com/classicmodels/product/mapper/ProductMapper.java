package com.classicmodels.product.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.commonentities.dto.ProductEvent;
import com.classicmodels.product.dto.ProductDTO;
import com.classicmodels.product.entities.Product;
import com.classicmodels.product.entities.ProductLine;
import com.classicmodels.product.repository.ProductLineRepository;

@Service
public class ProductMapper {
	
	@Autowired
	private ProductLineRepository productLineRepo; 

	public ProductDTO mapToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO(
				product.getProductCode(),
				product.getProductName(),
				product.getProductLine().getProductLine(),
				product.getProductScale(),
				product.getProductVendor(),
				product.getProductDescription(),
				product.getQuantityInStock(),
				product.getBuyPrice(),
				product.getMSRP()
				);
		return productDTO;
	}
	
	public Product mapToProduct(ProductDTO dto) {
		ProductLine productLine = null; 
		if(dto.productLine()!=null) {
			productLine = productLineRepo.findById(dto.productLine()).orElse(null);
		}
		Product product = new Product();
		product.setProductCode(dto.productCode());
		product.setProductName(dto.productName());
		product.setProductLine(productLine);
		product.setProductScale(dto.productScale());
		product.setProductVendor(dto.productVendor());
		product.setProductDescription(dto.productDescription());
		product.setQuantityInStock(dto.quantityInStock());
		product.setBuyPrice(dto.buyPrice());
		product.setMSRP(dto.MSRP());
		return product; 
	}
	
	public ProductEvent mapToEvent(ProductDTO dto, String type) {
		ProductEvent event = new ProductEvent(
				dto.productCode(),
				dto.productName(),
				dto.productScale(),
				dto.productVendor(),
				dto.productDescription(),
				dto.quantityInStock(),
				dto.buyPrice(),
				dto.MSRP(),
				type
				);
		return event; 
	}
}
