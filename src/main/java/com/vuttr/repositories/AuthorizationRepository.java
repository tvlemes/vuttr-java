package com.vuttr.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vuttr.models.Authorization;
import com.vuttr.models.User;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Long>{

	/* Query All Authorizations */
	@Query(value = "SELECT * FROM tb_authorization", 
			nativeQuery = true)
	Page<User> findAllAuthorizationCustom(Pageable pageable);

	/* Query Authorizations by Name */
	@Query(value = "SELECT * FROM tb_authorization WHERE unaccent(name) ilike unaccent('%' || ?1 || '%')", 
			nativeQuery = true)
	Page<User> findByNameCustom(String name, Pageable pageable);
	
	/* Query Authorization by Id */
	@Query("select u from Authorization u where u.id = ?1")
	User findByIdAuthorization(Long id);
}
