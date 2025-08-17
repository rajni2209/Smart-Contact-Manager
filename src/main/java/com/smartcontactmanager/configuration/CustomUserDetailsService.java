package com.smartcontactmanager.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartcontactmanager.Entity.Users;
import com.smartcontactmanager.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users byEmail = userRepository.findByEmail(username);
		if(byEmail == null) {
			throw new UsernameNotFoundException("can't find user");
		}
		return new CustomUserDetails(byEmail);
	}

}
