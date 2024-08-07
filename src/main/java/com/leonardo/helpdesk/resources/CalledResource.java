package com.leonardo.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leonardo.helpdesk.domain.Called;
import com.leonardo.helpdesk.domain.dtos.CalledDTO;
import com.leonardo.helpdesk.services.CalledService;

@RestController
@RequestMapping(value = "/called")
public class CalledResource {

	@Autowired
	private CalledService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<CalledDTO> findById(@PathVariable Integer id) {
		Called obj = service.findById(id);
		return ResponseEntity.ok().body(new CalledDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<CalledDTO>> findAll() {
		List<Called> list = service.findAll();
		List<CalledDTO> listDTO = list.stream().map(obj -> new CalledDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<CalledDTO> create(@Valid @RequestBody CalledDTO objDto) {
		CalledDTO newDto = new CalledDTO(service.create(objDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CalledDTO> update(@PathVariable Integer id, @Valid @RequestBody CalledDTO objDto) {
		Called newObj = service.update(id, objDto);
		return ResponseEntity.ok().body(new CalledDTO(newObj));
	}
}
