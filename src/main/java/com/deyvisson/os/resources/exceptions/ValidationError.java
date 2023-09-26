package com.deyvisson.os.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{


	private static final long serialVersionUID = 1L;
	
	private List<FieldMessade> erros = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String erro) {
		super(timestamp, status, erro);
	}

	public List<FieldMessade> getErros() {
		return erros;
	}

	public void addErros(String fieldName, String message) {
		this.erros.add(new FieldMessade(fieldName, message));
	}

	
}
