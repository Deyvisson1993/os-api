package com.deyvisson.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.deyvisson.os.dtos.OsDTO;
import com.deyvisson.os.services.OsService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OsResource {
	
	@Autowired
	private OsService service;
	
	//Lista OS por ID
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OsDTO> findByid(@PathVariable Integer id){
		OsDTO obj = new OsDTO(service.findByid(id));
		return ResponseEntity.ok().body(obj);
	}
	
	// Lista todas a OS
	
	@GetMapping
	public ResponseEntity<List<OsDTO>> findAll(){
		List<OsDTO> list = service.findall().stream().map(obj -> new OsDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	//Criar uma OS
	@PostMapping
	public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO obj){
		obj = new OsDTO(service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	//Atualiza a OS
	@PutMapping
	public ResponseEntity<OsDTO> update(@Valid @RequestBody OsDTO obj){
		obj = new OsDTO(service.update(obj));
		return ResponseEntity.ok().body(obj);
	}
}
