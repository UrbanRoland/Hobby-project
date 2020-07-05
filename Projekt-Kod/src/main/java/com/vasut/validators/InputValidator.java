package com.vasut.validators;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vasut.domain.User;
import com.vasut.repository.UserRepository;
import com.vasut.service.UserDetailsImpl;
import com.vasut.service.UserService;
import com.vasut.service.UserServiceImpl;

public class InputValidator implements Validator {
	private	UserServiceImpl userServiceImpl;
	private UserRepository userRepository;
	private UserService userService;

	
	@Override
	public boolean supports(Class<?> paramClass) {
	
		return User.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password","Kötlező kitölteni!");
	     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConf", "valid.passwordConf","Kötlező kitölteni!");
	     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "valid.fullName","Kötlező kitölteni!");
	     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "valid.email","Kötlező kitölteni!");
        User user = (User) obj;
       
        if (!user.getPassword().equals(user.getPasswordConf())) {
            errors.rejectValue("passwordConf", "valid.passwordConfDiff","A két jelszó nem egyezzik meg!");
        }
        
       if(matchEmail(user.getEmail())==false) {
        	 errors.rejectValue("email", "valid.email","Nem megfelelő az email formátuma!");
        }
        

        
        
        
	}
	
    public Boolean matchEmail(String content) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		if(p.matcher(content).find()) {
			 return true;
		}else {
			return false;
		}
}

	
	

}
