package com.classicmodels.security.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<Appuser, Long>{

	Optional<Appuser> findByUname(String uname);
}
