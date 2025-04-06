package com.autobidder.auth.repository;

import java.util.Optional;
import java.util.UUID;

import javax.management.relation.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, UUID> {
	Optional<Role> findByName(String name);
}