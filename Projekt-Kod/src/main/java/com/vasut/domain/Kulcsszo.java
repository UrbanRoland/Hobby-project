package com.vasut.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kulcsszo {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	// Legyen egyedi a nev oszlop
	@Column(unique = true)
	private String nev;

	public Kulcsszo() {
	}

	public Kulcsszo(String nev) {
		this.nev = nev;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

}
