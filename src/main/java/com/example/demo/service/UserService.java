package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User registerNewUser(User user) {
		if(userRepository.findByUsername(user.getUsername())!=null) {
			throw new UsernameExistException(user.getUsername());
		}
		user.setRole("ROLE_USER");
		user.setNotes(new ArrayList<Note>());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);	
	}
}
