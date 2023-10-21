package com.deyvisson.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyvisson.os.domain.Cliente;
import com.deyvisson.os.domain.OS;
import com.deyvisson.os.domain.Tecnico;
import com.deyvisson.os.domain.enuns.Prioridade;
import com.deyvisson.os.domain.enuns.Status;
import com.deyvisson.os.dtos.OsDTO;
import com.deyvisson.os.repositories.OSRepository;
import com.deyvisson.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class OsService {

	@Autowired
	private OSRepository repository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private TecnicoService tecnicoService;

	public OS findByid(Integer id) {

		Optional<OS> obj = repository.findById(id); // É opcional, ele pode ou n encontrar ID
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!: ID:" + id + ", Tipo: " + OS.class.getName()));

	}

	public List<OS> findall() {
		return repository.findAll();
	}

	public OS create(@Valid OsDTO obj) {
		return fromDTO(obj);
	}

	public OS update(@Valid OsDTO obj) {

		findByid(obj.getId());
		return fromDTO(obj);
	}

	private OS fromDTO(OsDTO obj) {
		OS newOBJ = new OS();
		newOBJ.setId(obj.getId());
		newOBJ.setObs(obj.getObservacao());
		newOBJ.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCod()));
		newOBJ.setStatus(Status.toEnum(obj.getStatus().getCod()));
		newOBJ.setId(obj.getId());

		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());

		newOBJ.setCliente(cli);
		newOBJ.setTenico(tec);
		
		if(newOBJ.getStatus().getCod().equals(2)){
			newOBJ.setDataFechamento(LocalDateTime.now());
		}

		return repository.save(newOBJ);
	}

}
