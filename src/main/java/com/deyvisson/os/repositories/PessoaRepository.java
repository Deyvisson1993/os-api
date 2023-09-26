package com.deyvisson.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deyvisson.os.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	//validação de CPF duplicado
	@Query("SELECT obj FROM Pessoa obj WHERE obj.cpf =:cpf")
	Pessoa findByCPF(@Param("cpf") String cpf);


}
