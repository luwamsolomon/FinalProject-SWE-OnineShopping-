package edu.mum.cs425.onlineshopping.service;

import edu.mum.cs425.onlineshopping.entity.Role;
import edu.mum.cs425.onlineshopping.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User findOne(String email);
    List<User> findByRoles(List<Role> role);
    void save(User user);
   
}
