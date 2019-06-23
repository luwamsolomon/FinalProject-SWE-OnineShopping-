package edu.mum.cs425.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs425.onlineshopping.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
