package edu.mum.cs425.onlineshopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs425.onlineshopping.entity.Role;
import edu.mum.cs425.onlineshopping.entity.User;
import edu.mum.cs425.onlineshopping.repository.UserRepository;
import edu.mum.cs425.onlineshopping.service.UserService;

import java.util.List;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email).get();
    }


    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       
            userRepository.save(user);
       
    }

   


	@Override
	public List<User> findByRoles(List<Role> roles) {
		return userRepository.findAllByRoles(roles);
	}

}
