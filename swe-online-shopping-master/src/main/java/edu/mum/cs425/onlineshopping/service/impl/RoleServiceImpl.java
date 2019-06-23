package edu.mum.cs425.onlineshopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs425.onlineshopping.entity.Role;
import edu.mum.cs425.onlineshopping.repository.RoleRepository;
import edu.mum.cs425.onlineshopping.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> getRoles() {
		
		return roleRepository.findAll();
	}
	

}
