package com.vasut.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//Ez egy POJO

@Entity
public class Felhasznalo {

	// Az adadtbazis dontse el, hogy milyen azonositot ad
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Ez a primary key fogja jelolni
	@Id
	private Long id;
	private String felhasznalonev;
	@Column(nullable = false)
	private String jelszo;
	@Column(unique = true, nullable = false)
	private String email;

	// Lehet tobb felhasznalo de minden flhasznalohoz pontosan egy jog tartozhat
	@ManyToOne
	private Jog jog;

	public Felhasznalo() {
	}

	public Felhasznalo(String felhasznalonev, String jelszo, String email, Jog jog) {
		this.felhasznalonev = felhasznalonev;
		this.jelszo = jelszo;
		this.email = email;
		this.jog = jog;
	}

	public Jog getJog() {
		return jog;
	}

	public void setJog(Jog jogId) {
		this.jog = jogId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFelhasznalonev() {
		return felhasznalonev;
	}

	public void setFelhasznalonev(String felhasznalonev) {
		this.felhasznalonev = felhasznalonev;
	}

	public String getJelszo() {
		return jelszo;
	}

	public void setJelszo(String jelszo) {
		this.jelszo = jelszo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
