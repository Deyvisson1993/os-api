package com.deyvisson.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.deyvisson.os.domain.Pessoa;
import com.deyvisson.os.domain.Cliente;
import com.deyvisson.os.dtos.ClienteDTO;
import com.deyvisson.os.repositories.PessoaRepository;
import com.deyvisson.os.repositories.ClienteRepository;
import com.deyvisson.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao Encontrado! Id:" + id + ", Tipo: " + Cliente.class.getName()));
	}

	// listar todos os Cliente
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	// Criar Cliente
	public Cliente create(ClienteDTO objDTO) {

		if (findByCPF(objDTO) != null) {
			throw new DataIntegrityViolationException("CPF ja Cadastrado na Base de Dados!");
		}

		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	// ATUALIZAR O Cliente

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente cliente = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegrityViolationException("CPF ja Cadastrado na Base de Dados!");

		}

		cliente.setNome(objDTO.getNome());
		cliente.setCpf(objDTO.getCpf());
		cliente.setTelefone(objDTO.getTelefone());
		return repository.save(cliente);
	}

	// DELETAR O Cliente
	public void delete(Integer id) {
		Cliente cliente =  findById(id);
		if(cliente.getList().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui O.S, não depe ser Deletado!");

		}
		repository.deleteById(id);

	}

	// Verificar o CPF se ja foi cadastrado
	private Pessoa findByCPF(ClienteDTO objDTO) {

		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
