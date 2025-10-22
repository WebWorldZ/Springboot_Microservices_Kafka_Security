package com.classicmodels.product.service;

import java.util.List;

import com.classicmodels.product.dto.ProductDTO;

public interface ProductService {

	ProductDTO add(ProductDTO req);
	
	List<ProductDTO> getAll();
	
	ProductDTO get(String productCode);
	
	ProductDTO update(String productCode,ProductDTO req);
	
	void delete(String productCode);
}
