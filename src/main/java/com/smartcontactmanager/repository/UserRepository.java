package com.smartcontactmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcontactmanager.Entity.Users;



@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	public Users findByEmail(String email);
	boolean existsByEmail(String email);
	
}
