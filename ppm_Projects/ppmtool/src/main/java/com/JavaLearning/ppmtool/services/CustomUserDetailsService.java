package com.JavaLearning.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JavaLearning.ppmtool.domain.User;
import com.JavaLearning.ppmtool.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		if(user==null) {
			 new UsernameNotFoundException("User not found");
		}
		return user;
	}
	@Transactional
	public User loadUserById(Long id) {
		User user = userRepository.getById(id);
		if(user==null) {
			 new UsernameNotFoundException("User not found");
		}
		return user;
	}

}
