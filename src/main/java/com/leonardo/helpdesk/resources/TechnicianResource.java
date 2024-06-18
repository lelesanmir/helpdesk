package com.leonardo.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.helpdesk.domain.Technician;
import com.leonardo.helpdesk.services.TechnicianService;

@RestController
@RequestMapping(value = "/technical")
public class TechnicianResource {

	@Autowired
	private TechnicianService service;

	@GetMapping(value = "/{id}")
	public Technician findById(@PathVariable Integer id) {
		return service.findById(id);
	}
}
