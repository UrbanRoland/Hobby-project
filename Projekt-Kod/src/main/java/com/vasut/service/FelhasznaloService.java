package com.vasut.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vasut.domain.Felhasznalo;
import com.vasut.domain.Jog;
import com.vasut.repository.FelhasznaloRepository;
import com.vasut.repository.JogRepository;

@Service
public class FelhasznaloService {

	private JogRepository jogRepo;
	private FelhasznaloRepository felhRepo;

	@Autowired
	public void setFelhRepo(FelhasznaloRepository felhRepo) {
		this.felhRepo = felhRepo;
	}

	@Autowired
	public void setJogRepo(JogRepository jogRepo) {
		this.jogRepo = jogRepo;
	}

	// Visszaadja az osszes felhasznalot
	public List<Felhasznalo> getFelhaszlalo() {
		return felhRepo.findAll();
	}

	// Felhasznalo mentese
	// Csak egyszer fusson le, ekkor automatikussan le fog futni
	// @PostConstruct
	public void init(String felhasznalonev, String jelszo, String email) {
		Felhasznalo felh = new Felhasznalo(felhasznalonev, jelszo, email, getAzonosito("felhasznalo"));
		felhRepo.save(felh);

	}

	// Regisztralni csak sima felhasznalok tudnak. Visszadja a parameterben megadott
	// jog nevet.
	public Jog getAzonosito(String nev) {
		return jogRepo.findByNev(nev);
	}
}
