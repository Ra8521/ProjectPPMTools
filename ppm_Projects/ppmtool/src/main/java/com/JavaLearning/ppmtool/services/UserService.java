package com.JavaLearning.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.JavaLearning.ppmtool.domain.User;
import com.JavaLearning.ppmtool.exceptions.UsernameAlreadyExistsException;
import com.JavaLearning.ppmtool.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser(User newUser) {
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			//user name has to be unique
			//make sure password and confirm password match
			//we don't persist the confirm password
			newUser.setConfirmPassword("******");
			return userRepository.save(newUser);
		}
		catch(Exception ex) {
			throw new UsernameAlreadyExistsException("Username is a already exists");
		}
		
	}
	
}
