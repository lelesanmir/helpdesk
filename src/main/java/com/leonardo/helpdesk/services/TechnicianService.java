package com.leonardo.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.helpdesk.domain.Technician;
import com.leonardo.helpdesk.domain.dtos.TechnicianDTO;
import com.leonardo.helpdesk.repositories.TechnicianRepository;
import com.leonardo.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TechnicianService {

	@Autowired
	private TechnicianRepository repository;

	public Technician findById(Integer id) {
		Optional<Technician> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id));
	}

	public List<Technician> findAll() {
		return repository.findAll();

	}

	public Technician create(TechnicianDTO objDTO) {
		objDTO.setId(null);
		Technician newObj = new Technician(objDTO);
		return repository.save(newObj);
	}
}
