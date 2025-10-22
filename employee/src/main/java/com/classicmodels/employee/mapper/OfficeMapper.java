package com.classicmodels.employee.mapper;

import org.springframework.stereotype.Service;

import com.classicmodels.employee.dto.OfficeDTO;
import com.classicmodels.employee.dto.OfficeRegistrationRequest;
import com.classicmodels.employee.entities.Office;

@Service
public class OfficeMapper {

	public Office mapToOffice(OfficeRegistrationRequest officeRequest) {
		Office office = new Office();
		office.setCity(officeRequest.city());
		office.setPhone(officeRequest.phone());
		office.setAddressLine1(officeRequest.addressLine1());
		office.setAddressLine2(officeRequest.addressLine2());
		office.setState(officeRequest.state());
		office.setCountry(officeRequest.country());
		office.setPostalcode(officeRequest.postalcode());
		office.setTerritory(officeRequest.territory());
		return office;
	}
	
	public OfficeDTO mapToOfficeDTO(Office office) {
		OfficeDTO officeDTO = new OfficeDTO(
				office.getOfficecode(),
				office.getCity(),
				office.getPhone(),
				office.getAddressLine1(),
				office.getAddressLine2(),
				office.getState(),
				office.getCountry(),
				office.getPostalcode(),
				office.getTerritory()
				);
		return officeDTO;
	}
	
}
