package com.classicmodels.product.service;

import java.util.List;

import com.classicmodels.product.dto.ProductLineDTO;

public interface ProductLineService {

	ProductLineDTO add(ProductLineDTO req);
	
	List<ProductLineDTO> getAll();
	
	ProductLineDTO get(String productLine);
	
	ProductLineDTO update(String productLine,ProductLineDTO req);
	
	void delete(String productLine);
}
