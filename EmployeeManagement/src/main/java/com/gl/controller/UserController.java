package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gl.entity.User;
import com.gl.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/getAllUsers")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<User> getAllUsers() {

		return userService.getAllUsers();
	}

	@PostMapping("/createNewUser")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User createNewUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.saveUsers(user);
	}

	@PutMapping("/updateUser")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User updateUser(@RequestBody User user) {

		int id = user.getId();
		User existingUser = userService.getUserById(id);
		existingUser.setId(id);
		existingUser.setUsername(user.getUsername());
		existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
		existingUser.setRoles(user.getRoles());

		return userService.updateUser(existingUser);
	}

	@DeleteMapping("/deleteUser")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteUser(int id) {
		userService.deleteUserById(id);
		return "User deleted successfully";
	}

}
