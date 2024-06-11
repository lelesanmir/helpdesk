package com.leonardo.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
	
	private List<Called> called = new ArrayList<>();

	public Client() {
		super();
	}

	public Client(Integer id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
	}

	public List<Called> getCalled() {
		return called;
	}

	public void setCalled(List<Called> called) {
		this.called = called;
	}
	
	
}