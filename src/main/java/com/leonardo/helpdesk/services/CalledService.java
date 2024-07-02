package com.leonardo.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.helpdesk.domain.Called;
import com.leonardo.helpdesk.domain.Client;
import com.leonardo.helpdesk.domain.Technician;
import com.leonardo.helpdesk.domain.dtos.CalledDTO;
import com.leonardo.helpdesk.enums.Priority;
import com.leonardo.helpdesk.enums.Status;
import com.leonardo.helpdesk.repositories.CalledRepository;
import com.leonardo.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class CalledService {

	@Autowired
	private CalledRepository repository;
	@Autowired
	private TechnicianService technicianService;
	@Autowired
	private ClientService clientService;

	public Called findById(Integer id) {
		Optional<Called> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! ID: " + id));
	}

	public List<Called> findAll() {
		return repository.findAll();
	}

	public Called create(@Valid CalledDTO objDto) {
		return repository.save(newCalled(objDto));
	}

	private Called newCalled(CalledDTO obj) {
		Technician technician = technicianService.findById(obj.getTechnician());
		Client client = clientService.findById(obj.getClient());

		Called called = new Called();
		if (obj.getId() != null) {
			called.setId(obj.getId());
		}

		called.setTechnician(technician);
		called.setClient(client);
		called.setPriority(Priority.toEnum(obj.getPriority()));
		called.setStatus(Status.toEnum(obj.getStatus()));
		called.setTitle(obj.getTitle());
		called.setObservation(obj.getObservation());
		return called;
	}

}
