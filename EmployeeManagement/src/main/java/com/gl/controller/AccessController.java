package com.gl.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {

	@RequestMapping("/403")
	@PreAuthorize("permitAll()")
	public String accessDenied(Principal user) {

		String message = "Hi " + user.getName() + " you don't have permission to access this page";
		return message;
	}
}
