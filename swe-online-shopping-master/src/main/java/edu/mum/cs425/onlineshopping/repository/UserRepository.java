package edu.mum.cs425.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs425.onlineshopping.entity.Role;
import edu.mum.cs425.onlineshopping.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findAllByRoles(List<Role> role);

}
