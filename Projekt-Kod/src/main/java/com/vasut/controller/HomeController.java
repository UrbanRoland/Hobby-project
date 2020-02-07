package com.vasut.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vasut.domain.News;
import com.vasut.domain.Tickets;
import com.vasut.domain.User;
import com.vasut.repository.NewsRepository;
import com.vasut.service.EmailService;
import com.vasut.service.TicketsService;
import com.vasut.service.UserService;

@Controller
public class HomeController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private EmailService emailService;
	private UserService userService;
	private NewsRepository newsRepo;
	private TicketsService ticketsService;

	@Autowired
	public void setTicketsService(TicketsService ticketsService) {
		this.ticketsService = ticketsService;
	}

	@Autowired
	public void setNewsRepo(NewsRepository newsRepo) {
		this.newsRepo = newsRepo;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@RequestMapping("/index")
	public String stories() {
		return "index";
	}

	@RequestMapping(value = "/reggg", method = RequestMethod.POST)
	public String felhasznaloMentes(@ModelAttribute User user) {


      // emailService.sendMessage(user.getEmail());
		userService.registerUser(user);
		return "auth/login";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(HttpServletRequest rA, Exception ex, Model model) {
		model.addAttribute("errMessage", ex.getMessage());
		return "exceptionHandler";

	}

	
	@RequestMapping("bejelentkezesUtan")
	public String bejelentkezve(Model model) {
		// jelenjenek meg hirrek
		model.addAttribute("news", newsRepo.findFirst5ByOrderByDateDesc());
		return "bejelentkezesUtan";
	}

	// Ez tartozik a hírek kereséséhez
	@RequestMapping("search")
	public String atiranyit(@ModelAttribute("keyword") String keyword, Model model) {
		model.addAttribute("news", newsRepo.search(keyword));
		return "bejelentkezesUtan";
	}

	@RequestMapping("/hirek")
	public String hirek(Model model) {
		model.addAttribute("menetjegyek", new Tickets());
		model.addAttribute("user", new User());
		return "hirek";
	}

	@RequestMapping(value = "/menet", method = RequestMethod.POST)
	public String cimvisszaadasa(@ModelAttribute News news) {
		return "bejelentkezesUtan";
	}

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("news", newsRepo.findFirst5ByOrderByDateDesc());
		return "bejelentkezesUtan";
	}

	@RequestMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}


	@PostMapping("/reg")
	public String saveUser(@ModelAttribute User user) {
		emailService.uzenetKuldese(user.getEmail());
		userService.registerUser(user);
		return "auth/login";
	}

	@RequestMapping("/jegyeim")
	public String jegyek(Model model) {
		return "jegyeim";
	}

	@RequestMapping("/profil")
	public String profil(Model model) {
		return "profil";
	}

	@RequestMapping("jegyek")
	public String asddsa(@ModelAttribute("from") String from, @ModelAttribute("too") String too,
			@ModelAttribute("time") String time, Model model) {
		model.addAttribute("tickets", ticketsService.getTickets(from, too, time));
		return "jegyvasarlas";
	}

	@RequestMapping("/jegyvasarlas")
	public String menetrendFelhasznaloknak(Model model) {
		model.addAttribute("tickets", ticketsService.getTickets());
		return "jegyvasarlas";
	}

	@RequestMapping("/menetrendVendegnek")
	public String menetrendVendegeknek(Model model) {
		return "menetrendVendegnek";
	}

}
