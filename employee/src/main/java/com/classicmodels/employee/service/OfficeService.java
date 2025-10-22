package com.classicmodels.employee.service;

import java.util.List;

import com.classicmodels.employee.dto.OfficeDTO;
import com.classicmodels.employee.dto.OfficeRegistrationRequest;

public interface OfficeService {

	OfficeDTO add(OfficeRegistrationRequest office);
	
	List<OfficeDTO> getAll();
	
	List<OfficeDTO> getAllPaginated(Integer page, Integer size);
	
	OfficeDTO get(Integer id);
	
	OfficeDTO update(Integer id, OfficeRegistrationRequest office);
	
	void delete(Integer id);
}
