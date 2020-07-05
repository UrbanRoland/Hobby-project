package com.vasut.service;

import java.util.List;

import com.vasut.domain.User;

public interface UserService {

	public String registerUser(User user);

	public User findByEmail(String email);
	
	public List<String> allEmail();

	public String userActivation(String code);
}
