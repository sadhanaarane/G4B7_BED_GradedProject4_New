package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gl.entity.Role;
import com.gl.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RolesController {

	@Autowired
	private RoleService rolesService;

	@GetMapping("/getAllRoles")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Role> getAllRoles() {

		return rolesService.getAllRoles();
	}

	@GetMapping("/getRoleById")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Role getRoleById(int id) {

		return rolesService.getRoleById(id);
	}

	@PostMapping("/createNewRole")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Role createNewRole(@RequestBody Role role) {

		return rolesService.saveRoles(role);
	}

	@PutMapping("/updateRole")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Role updateRole(@RequestBody Role role) {

		int id = role.getId();

		Role existingRole = rolesService.getRoleById(id);
		existingRole.setId(id);
		existingRole.setName(role.getName());

		return rolesService.updateRole(existingRole);
	}

	@DeleteMapping("/deleteRole")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteRole(int id) {
		rolesService.deleteRoleById(id);
		return "Role deleted successfully";
	}
}
