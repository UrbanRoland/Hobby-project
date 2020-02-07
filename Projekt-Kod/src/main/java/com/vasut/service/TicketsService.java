package com.vasut.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vasut.domain.Tickets;
import com.vasut.repository.TicketsRepository;

@Service
public class TicketsService {

	private TicketsRepository ticketsRepo;

	@Autowired
	public void setTicketsRepo(TicketsRepository ticketsRepo) {
		this.ticketsRepo = ticketsRepo;
	}

	public List<Tickets> getTickets() {
		return ticketsRepo.findAll();
	}

	public List<Tickets> getTickets(String from, String too, String time) {
		return ticketsRepo.getJegyek(from, too, time);
	}

}
