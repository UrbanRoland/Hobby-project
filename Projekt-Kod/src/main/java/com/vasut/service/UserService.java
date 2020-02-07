package com.vasut.service;

import com.vasut.domain.User;

public interface UserService {

	public void registerUser(User user);

	public User findByEmail(String email);
}
