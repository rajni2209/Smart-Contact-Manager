package com.smartcontactmanager.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contacts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Cid;
	private String FirstName;
	private String SecoundName;
	private String work;
	private String email;
	private String phone;
	private String image;
	private String description;
	
	@ManyToOne
	private Users users;
}
