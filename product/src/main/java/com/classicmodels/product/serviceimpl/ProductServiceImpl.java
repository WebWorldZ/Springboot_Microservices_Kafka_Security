package com.classicmodels.product.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classicmodels.commonentities.dto.ProductEvent;
import com.classicmodels.product.dto.ProductDTO;
import com.classicmodels.product.entities.Product;
import com.classicmodels.product.entities.ProductLine;
import com.classicmodels.product.kafka.ProductProducer;
import com.classicmodels.product.mapper.ProductMapper;
import com.classicmodels.product.repository.ProductLineRepository;
import com.classicmodels.product.repository.ProductRepository;
import com.classicmodels.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductLineRepository productLineRepo;
	
	@Autowired
	private ProductProducer productProducer; 
	
	@Override
	public ProductDTO add(ProductDTO req) {
		Product product = productMapper.mapToProduct(req);
		Product saved = productRepo.save(product);
		ProductDTO productDTO = productMapper.mapToProductDTO(saved);
		ProductEvent event = productMapper.mapToEvent(productDTO, "CREATE");
		productProducer.sendEvent(event);
		return productDTO; 
	}

	@Override
	public List<ProductDTO> getAll() {
		return productRepo.findAll()
				.stream()
				.map(e-> productMapper.mapToProductDTO(e))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO get(String productCode) {
		return productRepo.findById(productCode)
				.map(e-> productMapper.mapToProductDTO(e))
				.orElseThrow(()-> new RuntimeException("Product with product code "+productCode+" not found"));
	}

	@Override
	public ProductDTO update(String productCode, ProductDTO req) {
		Product productDB = productRepo.findById(productCode).orElseThrow(()-> new RuntimeException("Product with product code "+productCode+" not found"));
		if (req.productCode()!= null && !req.productCode().equals(productDB.getProductCode())) {
			productDB.setProductCode(req.productCode());
	        }
		if (req.productName()!= null && !req.productName().equals(productDB.getProductName())) {
			productDB.setProductName(req.productName());
	        }
		if (req.productLine()!= null) {
			ProductLine productLine = productLineRepo.findById(req.productLine()).orElseThrow(()-> new RuntimeException("ProductLine with product line "+req.productLine()+" not found"));
			 if(productDB.getProductLine() == null) {
				 productDB.setProductLine(productLine);
			 }else if(productDB.getProductLine() != null && !req.productLine().equals(productDB.getProductLine().getProductLine())){
				 productDB.setProductLine(productLine);
			 }
	        }
		if (req.productScale()!= null && !req.productScale().equals(productDB.getProductScale())) {
			productDB.setProductScale(req.productScale());
	        }
		if (req.productVendor()!= null && !req.productVendor().equals(productDB.getProductVendor())) {
			productDB.setProductVendor(req.productVendor());
	        }
		if (req.productDescription()!= null && !req.productDescription().equals(productDB.getProductDescription())) {
			productDB.setProductDescription(req.productDescription());
	        }
		if (req.quantityInStock()!= null && req.quantityInStock().compareTo(productDB.getQuantityInStock()) != 0) {
			productDB.setQuantityInStock(req.quantityInStock());
	        }
		if (req.buyPrice()!= null && req.buyPrice().compareTo(productDB.getBuyPrice()) != 0) {
			productDB.setBuyPrice(req.buyPrice());
	        }
		if (req.MSRP()!= null && req.MSRP().compareTo(productDB.getMSRP()) != 0) {
			productDB.setMSRP(req.MSRP());
	        }
		Product saved = productRepo.save(productDB);
		ProductDTO productDTO = productMapper.mapToProductDTO(saved);
		ProductEvent event = productMapper.mapToEvent(productDTO, "UPDATE");
		productProducer.sendEvent(event);
		return productDTO; 
	}

	@Override
	public void delete(String productCode) {
		ProductDTO productDTO = productRepo.findById(productCode)
		.map(e-> productMapper.mapToProductDTO(e))
		.orElseThrow(()-> new RuntimeException("Product with product code "+productCode+" not found"));
		productRepo.deleteById(productCode);
		ProductEvent event = productMapper.mapToEvent(productDTO, "DELETE");
		productProducer.sendEvent(event);
	}

}
