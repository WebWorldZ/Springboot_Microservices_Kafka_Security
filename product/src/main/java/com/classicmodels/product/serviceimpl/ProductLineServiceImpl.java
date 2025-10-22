package com.classicmodels.product.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.product.dto.ProductLineDTO;
import com.classicmodels.product.entities.ProductLine;
import com.classicmodels.product.mapper.ProductLineMapper;
import com.classicmodels.product.repository.ProductLineRepository;
import com.classicmodels.product.service.ProductLineService;

@Service
public class ProductLineServiceImpl implements ProductLineService {

	@Autowired
	private ProductLineRepository productLineRepo;
	
	@Autowired
	private ProductLineMapper productLineMapper;

	@Override
	public ProductLineDTO add(ProductLineDTO req) {
		ProductLine productLine = productLineMapper.mapToProductLine(req);
		ProductLine saved = productLineRepo.save(productLine);
		ProductLineDTO productLineDTO = productLineMapper.mapToProductLineDTO(saved);
		return productLineDTO;
	}

	@Override
	public List<ProductLineDTO> getAll() {
		return productLineRepo.findAll()
				.stream()
				.map(e-> productLineMapper.mapToProductLineDTO(e))
				.collect(Collectors.toList());
	}

	@Override
	public ProductLineDTO get(String productLine) {
		return productLineRepo.findById(productLine)
				.map(e-> productLineMapper.mapToProductLineDTO(e))
				.orElseThrow(()-> new RuntimeException("ProductLine with productline "+productLine+" not found"));
	}

	@Override
	public ProductLineDTO update(String productLine, ProductLineDTO req) {
		ProductLine productLineDB = productLineRepo.findById(productLine).orElseThrow(()-> new RuntimeException("ProductLine with productline "+productLine+" not found"));
		if (req.productLine()!= null && !req.productLine().equals(productLineDB.getProductLine())) {
			productLineDB.setProductLine(req.productLine());
	        }
		if (req.textDescription()!= null && !req.textDescription().equals(productLineDB.getTextDescription())) {
			productLineDB.setTextDescription(req.textDescription());
	        }
		if (req.htmlDescription()!= null && !req.htmlDescription().equals(productLineDB.getHtmlDescription())) {
			productLineDB.setHtmlDescription(req.htmlDescription());
	        }
		if (req.image()!= null && !req.image().equals(productLineDB.getImage())) {
			productLineDB.setImage(req.image());
	        }
		ProductLine saved = productLineRepo.save(productLineDB);
		ProductLineDTO productLineDTO = productLineMapper.mapToProductLineDTO(saved);
		return productLineDTO;
	}

	@Override
	public void delete(String productLine) {
		productLineRepo.deleteById(productLine);
	}
}
