package com.deyvisson.os.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import com.deyvisson.os.domain.Cliente;

import jakarta.validation.constraints.NotEmpty;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "O campo 'NOME' não pode ser nulo")
	private String nome;
	@CPF
	private String cpf;
	@NotEmpty(message = "O campo 'TELEFONE' não pode ser nulo")
	private String telefone;

	public ClienteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteDTO(Cliente obg) {
		super();
		this.id = obg.getId();
		this.nome = obg.getNome();
		this.cpf = obg.getCpf();
		this.telefone = obg.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
