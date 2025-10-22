package com.classicmodels.employee.service.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.classicmodels.employee.dto.OfficeDTO;
import com.classicmodels.employee.dto.OfficeRegistrationRequest;
import com.classicmodels.employee.entities.Office;
import com.classicmodels.employee.exception.ResourceNotFoundException;
import com.classicmodels.employee.mapper.OfficeMapper;
import com.classicmodels.employee.repository.OfficeRepository;
import com.classicmodels.employee.service.OfficeService;

@Service
public class OfficeServiceImpl implements OfficeService{
	
	@Autowired
	private OfficeMapper officeMapper;
	
	@Autowired
	private OfficeRepository officeRepo; 

	@Override
	public OfficeDTO add(OfficeRegistrationRequest officeReq) {
		Office office = officeMapper.mapToOffice(officeReq);
		Office saved = officeRepo.save(office);
		return officeMapper.mapToOfficeDTO(saved);
	}

	@Override
	public List<OfficeDTO> getAll() {
		return officeRepo.findAll()
				.stream()
				.map( e -> officeMapper.mapToOfficeDTO(e))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<OfficeDTO> getAllPaginated(Integer page, Integer size) {
		PageRequest pageReq = PageRequest.of(page, size);
		Page<Office> officePage = officeRepo.findAll(pageReq);
		List<Office> officeList = officePage.getContent();
		
		return officeList.stream().map(e -> officeMapper.mapToOfficeDTO(e))
		.collect(Collectors.toList());
	}


	@Override
	public OfficeDTO get(Integer id) {
		return officeRepo.findById(id)
				.map(e -> officeMapper.mapToOfficeDTO(e))
				.orElseThrow(()-> new ResourceNotFoundException("Office with id "+id+" not found"));
	}

	@Override
	public void delete(Integer id) {
		officeRepo.deleteById(id);
	}

	@Override
	public OfficeDTO update(Integer id, OfficeRegistrationRequest updateReq) {
		 Office officeDB = officeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Office with id "+id+" not found"));
		 
		 if (updateReq.city() != null && !updateReq.city().equals(officeDB.getCity())) {
			 officeDB.setCity(updateReq.city());
	        }
		 if (updateReq.phone() != null && !updateReq.phone().equals(officeDB.getPhone())) {
			 officeDB.setPhone(updateReq.phone());
	        }
		 if (updateReq.addressLine1() != null && !updateReq.addressLine1().equals(officeDB.getAddressLine1())) {
			 officeDB.setAddressLine1(updateReq.addressLine1());
	        }
		 if (updateReq.addressLine2() != null && !updateReq.addressLine2().equals(officeDB.getAddressLine2())) {
			 officeDB.setAddressLine2(updateReq.addressLine2());
	        }
		 if (updateReq.state() != null && !updateReq.state().equals(officeDB.getState())) {
			 officeDB.setState(updateReq.state());
	        }
		 if (updateReq.country() != null && !updateReq.country().equals(officeDB.getCountry())) {
			 officeDB.setCountry(updateReq.country());
	        }
		 if (updateReq.postalcode() != null && !updateReq.postalcode().equals(officeDB.getPostalcode())) {
			 officeDB.setPostalcode(updateReq.postalcode());
	        }
		 if (updateReq.territory() != null && !updateReq.territory().equals(officeDB.getTerritory())) {
			 officeDB.setTerritory(updateReq.territory());
	        }
		
		Office saved = officeRepo.save(officeDB);
		return officeMapper.mapToOfficeDTO(saved);
	}

	
}
