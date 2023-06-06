package com.social.media.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.social.media.user.User;
import com.social.media.user.UserDAOService;

import jakarta.validation.Valid;

@RestController
public class SocialMediaController {
	
	@Autowired
	private UserDAOService userDAOService;
	
	@GetMapping("/users")
	public List<User> retrieveAll(){
		
		return userDAOService.findAll();
		
	}
	
//	@GetMapping(path="/users/{id}")
//	public User findById(@PathVariable Integer id) {
//		
//		return userDAOService.findById(id);
//		
//	}
	/**next version of the above method**/
	/**this version is to experiment or try HATEOS**/
	@GetMapping(path="/users/{id}")
	public EntityModel<User> findById(@PathVariable Integer id) {
		
		User user = userDAOService.findById(id);
		/**wrap the user with EntityModel and then use linkBuilder to create the link and add to the entityModel**/
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAll());
		entityModel.add(link.withRel("all the users"));
		return entityModel;
		
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteById(@PathVariable Integer id) {
		
		
		
		 userDAOService.deleteById(id);
		
	}
	
	@PostMapping("/users/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		
		user= userDAOService.saveUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return  ResponseEntity.created(location).build();
		
	}

}
