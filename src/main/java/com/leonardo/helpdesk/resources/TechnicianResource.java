package com.leonardo.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leonardo.helpdesk.domain.Technician;
import com.leonardo.helpdesk.domain.dtos.TechnicianDTO;
import com.leonardo.helpdesk.services.TechnicianService;

@RestController
@RequestMapping(value = "/technical")
public class TechnicianResource {

	@Autowired
	private TechnicianService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id) {
		Technician obj = service.findById(id);
		return ResponseEntity.ok().body(new TechnicianDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<TechnicianDTO>> findAll() {
		List<Technician> list = service.findAll();
		List<TechnicianDTO> listDTO = list.stream().map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO objDTO) {
		Technician newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TechnicianDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicianDTO objDto) {
		Technician obj = service.update(id, objDto);
		return ResponseEntity.ok().body(new TechnicianDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TechnicianDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}










