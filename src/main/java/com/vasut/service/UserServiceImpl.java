package com.vasut.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vasut.domain.Role;
import com.vasut.domain.Tickets;
import com.vasut.domain.User;
import com.vasut.repository.RoleRepository;
import com.vasut.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final String USER_ROLE = "USER";
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private EmailService emailService;
	
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}


	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	@Override
	public List<String> allEmail() {
		return userRepository.allEmail();
	}

	@Override
	public String registerUser(User userToRegister) {
		User userCheck = userRepository.findByEmail(userToRegister.getEmail());

		if (userCheck != null)
			return "alreadyExists";

		Role userRole = roleRepository.findByRole(USER_ROLE);
		if (userRole != null) {
			userToRegister.getRoles().add(userRole);
		} else {
			userToRegister.addRoles(USER_ROLE);
		}
		String key=generateKey();
		userToRegister.setEnabled(false);
		userToRegister.setActivation(key);
		userRepository.save(userToRegister);
		emailService.uzenetKuldese(userToRegister.getEmail(),key);

		return "ok";
	}

	public String generateKey()
    {
		String key = "";
		Random random = new Random();
		char[] word = new char[16]; 
		for (int j = 0; j < word.length; j++) {
			word[j] = (char) ('a' + random.nextInt(26));
		}
		String toReturn = new String(word);
		//log.debug("random code: " + toReturn);
		//System.out.println("Aktivációs kód: " + toReturn);
		return new String(word);
    }

	@Override
	public String userActivation(String code) {
		User user = userRepository.findByActivation(code);
		if (user == null)
		    return "noresult";
		
		user.setEnabled(true);
		user.setActivation("");
		userRepository.save(user);
		
		return "ok";
	}
	
	
}
