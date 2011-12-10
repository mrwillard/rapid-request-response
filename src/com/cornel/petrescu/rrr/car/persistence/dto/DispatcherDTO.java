package com.cornel.petrescu.rrr.car.persistence.dto;

import java.io.Serializable;

public class DispatcherDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String nume;
	private String telefon;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

}
