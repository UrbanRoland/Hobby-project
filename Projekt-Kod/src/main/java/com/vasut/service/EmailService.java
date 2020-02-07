package com.vasut.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final Log log = LogFactory.getLog(this.getClass());

	@Value("${spring.mail.username}")
	private String UZENET_FELADO;

	private JavaMailSender javaMailSender;

	// Azt us lehetne, hogy a javaMailSender fole irom az anotaciot de ez
	// tesztelhetoseg szempontjabol jobb
	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	// Annak az email cimet varja parameterul aki beregisztrált tehat ez lesz a
	// cimzett
	public void uzenetKuldese(String email) {
		// Ez lesz az az objektum amit tartalommal toltunk fel
		SimpleMailMessage uzenet = null;

		try {

			uzenet = new SimpleMailMessage();
			// Ki a felado
			uzenet.setFrom(UZENET_FELADO);
			// Kinek kuldjuk
			uzenet.setTo(email);
			// Ez az uzenet targya
			uzenet.setSubject("Sikeres Regisztrálás");
			// Maga az uzenet
			uzenet.setText("Kedves " + email + "! \n \n Köszönjük, hogy regisztráltál az oldalunkra!");
			javaMailSender.send(uzenet);
		} catch (Exception e) {
			log.info("Hiba az email küldésekor az alábbi címre: " + email + " " + e);
		}
	}

}
