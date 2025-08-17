package com.smartcontactmanager.usercontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.smartcontactmanager.Entity.Contacts;
import com.smartcontactmanager.Entity.Users;
import com.smartcontactmanager.repository.ContactRepository;
import com.smartcontactmanager.repository.UserRepository;


@Controller
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	ContactRepository contactRepository;

	
	
	//method to add comon data in all the page
	@ModelAttribute
	public void commonData(Model model , Principal principal) {
		String userName = principal.getName();
		
		Users fullUser = userRepository.findByEmail(userName);
		model.addAttribute("user", fullUser);
	}
	
	
	// returning the dashboard
	@GetMapping("/dashboard")
	public String userDashboard(Model model, Principal principal) {

	    String username = principal.getName(); // logged-in user's username/email
	    Users user = userRepository.findByEmail(username);

	    long total = contactRepository.countByUser(user);

	    model.addAttribute("name", user.getName());
	    model.addAttribute("email", user.getEmail());
	    model.addAttribute("role", user.getRole());
	    model.addAttribute("total", total);

	    return "/normal/user-dashboard"; // or whatever your template name is
	}

	
	
	// add-contact
	@GetMapping("/add-contact")
	public String addContact(Model model) {
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contacts());
		return "/normal/add-contact";
	}
	
	@PostMapping("/add-contact-processing")
	public String processContact(@ModelAttribute Contacts contacts, @RequestParam("profileImage") MultipartFile file,
			Principal principal) {

		try {

			String name = principal.getName(); // Fetch logged-in user's email
			Users user = userRepository.findByEmail(name); // Fetch user object from DB

			// file storage
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				contacts.setImage(fileName);

				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				System.out.println("image uploaded");

			}

			contacts.setUsers(user); // Set user into contact
			user.getContacts().add(contacts); // Add contact to user’s contact list
			userRepository.save(user); // Save everything to DB

			System.out.println("Added successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/user/view-contacts";
	}
	
	
	@GetMapping("/view-contacts/{page}")
	public String showContact(@PathVariable("page") int page , Model model , Principal principal) {
		model.addAttribute("title", "AllContact");
		String name = principal.getName();
		Users user = userRepository.findByEmail(name);
		
		Pageable pageable = PageRequest.of(page , 5);
		Page<Contacts> contactsByUser = contactRepository.findContactsByUser(user.getId() , pageable);
		
		model.addAttribute("contact", contactsByUser);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPage", contactsByUser.getTotalPages());
		
		
		return "normal/view-contacts";
	}
	
	@GetMapping("/view-contacts")
	public String redirectToFirstPage() {
	    return "redirect:/user/view-contacts/0";
	}

	
	@GetMapping("/contact/{Cid}")
	public String showContactDetails(@PathVariable("Cid") Integer cid, Principal principal ,Model model) {
		
		Optional<Contacts> byId = contactRepository.findById(cid);
		Contacts contacts = byId.get();
		String name = principal.getName();
		Users user = userRepository.findByEmail(name);
		
		if(user.getId() == contacts.getUsers().getId()) {
			model.addAttribute("contacts", contacts);			
		}
		
		
		return "normal/contacts";
	}
	
	@GetMapping("/delete/{Cid}")
	public String deleteContact(@PathVariable("Cid") Integer Cid , Principal principal , Model model) {
		
		Optional<Contacts> byId = contactRepository.findById(Cid);
		Contacts contacts = byId.get();
		
		String name = principal.getName();
		Users byEmail = userRepository.findByEmail(name);
		if(byEmail.getId() == contacts.getUsers().getId()) {
			contactRepository.delete(contacts);
		}
		return "redirect:/user/view-contacts/0";
	}
	
	
	@GetMapping("/update-contact/{Cid}")
	public String updateContact(@PathVariable("Cid") Integer Cid, Model model) {
		
		Optional<Contacts> byId = contactRepository.findById(Cid);
		Contacts contacts = byId.get();
		model.addAttribute("contact", contacts);
		
		return "normal/update-contact";
		
	}
	
	
	@PostMapping("/update-contact-processing")
	public String updateContactProcessing(@ModelAttribute Contacts contacts, @RequestParam("profileImage") MultipartFile file , Model model , Principal principal) throws IOException {
		
		String name = principal.getName();
		Users byEmail = userRepository.findByEmail(name);
		
		Contacts oldContacts = contactRepository.findById(contacts.getCid()).get();
		
		if(!file.isEmpty()) {
			if(oldContacts.getImage() != null && !oldContacts.getImage().equals("default.png")) {
				Path oldImagePath = Paths.get("static/img", oldContacts.getImage());
				Files.deleteIfExists(oldImagePath);
			}
			
			String newFilename = file.getOriginalFilename();
			contacts.setImage(newFilename);
			
			File file2 = new ClassPathResource("static/img").getFile();
			Path path = Paths.get(file2.getAbsolutePath() + File.separator + file.getOriginalFilename());
			Files.copy(file.getInputStream() , path , StandardCopyOption.REPLACE_EXISTING);
		
		}
		
		contacts.setUsers(byEmail);
		contactRepository.save(contacts);
		
		
		return "redirect:/user/view-contacts/0";
	}
	
	
	@GetMapping("/profile")
	public String profile() {
		return "normal/profile";
	}
	
	
	
	
}
