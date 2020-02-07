package com.vasut.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tickets {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String fromm;
	private String too;
	private String date;
	private String departure;
	private String arrival;
	private int price;

	public Tickets() {
	}

	public Tickets(Long id, String fromm, String too, String date, String departure, String arrival, int price) {
		this.id = id;
		this.fromm = fromm;
		this.too = too;
		this.date = date;
		this.departure = departure;
		this.arrival = arrival;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return fromm;
	}

	public void setFrom(String from) {
		this.fromm = from;
	}

	public String getToo() {
		return too;
	}

	public void setToo(String too) {
		this.too = too;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
