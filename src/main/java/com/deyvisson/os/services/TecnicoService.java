package com.deyvisson.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.deyvisson.os.domain.Pessoa;
import com.deyvisson.os.domain.Tecnico;
import com.deyvisson.os.dtos.TecnicoDTO;
import com.deyvisson.os.repositories.PessoaRepository;
import com.deyvisson.os.repositories.TecnicoRepository;
import com.deyvisson.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao Encontrado! Id:" + id + ", Tipo: " + Tecnico.class.getName()));
	}

	// listar todos os tecnicos
	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	// Criar tecnico
	public Tecnico create(TecnicoDTO objDTO) {

		if (findByCPF(objDTO) != null) {
			throw new DataIntegrityViolationException("CPF ja Cadastrado na Base de Dados!");
		}

		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	// ATUALIZAR O TECNICO

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico tecnico = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegrityViolationException("CPF ja Cadastrado na Base de Dados!");

		}

		tecnico.setNome(objDTO.getNome());
		tecnico.setCpf(objDTO.getCpf());
		tecnico.setTelefone(objDTO.getTelefone());
		return repository.save(tecnico);
	}

	// DELETAR O TECNICO
	public void delete(Integer id) {
		Tecnico tecnico =  findById(id);
		if(tecnico.getList().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui O.S, não depe ser Deletado!");

		}
		repository.deleteById(id);

	}

	// Verificar o CPF se ja foi cadastrado
	private Pessoa findByCPF(TecnicoDTO objDTO) {

		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
