package com.vasut.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Jog {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String nev;

	// Egy jog lehet de tartozhat egy joghoz tobb felhasznalo
	@OneToMany(mappedBy = "jog")
	private List<Felhasznalo> felhasznalok;

	public Jog() {
	}

	public Jog(String nev, List<Felhasznalo> felhasznalok) {
		this.nev = nev;
		this.felhasznalok = felhasznalok;
	}

	public List<Felhasznalo> getFelhasznalok() {
		return felhasznalok;
	}

	public void setFelhasznalok(List<Felhasznalo> felhasznalok) {
		this.felhasznalok = felhasznalok;
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
