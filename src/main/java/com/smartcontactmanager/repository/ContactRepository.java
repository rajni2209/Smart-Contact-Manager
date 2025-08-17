package com.smartcontactmanager.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartcontactmanager.Entity.Contacts;
import com.smartcontactmanager.Entity.Users;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, Integer> {
	
	@Query("from Contacts as c where c.users.id = :userID")
	public Page<Contacts> findContactsByUser(@Param("userID") int userID , Pageable pageable);
	
	@Query("SELECT COUNT(c) FROM Contacts c WHERE c.users = :user")
	long countByUser(@Param("user") Users user);

	
}
