package com.deyvisson.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deyvisson.os.domain.Cliente;
import com.deyvisson.os.domain.OS;
import com.deyvisson.os.domain.Tecnico;
import com.deyvisson.os.domain.enuns.Prioridade;
import com.deyvisson.os.domain.enuns.Status;
import com.deyvisson.os.repositories.ClienteRepository;
import com.deyvisson.os.repositories.OSRepository;
import com.deyvisson.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public void instaciaDB() {

//		Tecnico t1 = new Tecnico(null, "Deyvisson M Araujo", "15660356001", "(81)99637-5872");
//		Tecnico t2 = new Tecnico(null, "Paulo Abreu da Silva", "34155914093", "(81)98725-3975");
//
//		Cliente c1 = new Cliente(null, "Betina Campos", "51306496055", "(81)99637-7788");
//		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ABERTO, t1, c1);
//
//		t1.getList().add(os1);
//		c1.getList().add(os1);
//
//		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
//		clienteRepository.saveAll(Arrays.asList(c1));
//		osRepository.saveAll(Arrays.asList(os1));
	}
}