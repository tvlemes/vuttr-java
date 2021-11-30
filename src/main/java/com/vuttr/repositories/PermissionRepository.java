package com.vuttr.repositories;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vuttr.models.Permission;
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{
	
	/* Query All Permissions */
	@Query(value = "SELECT * FROM tb_permission", 
			nativeQuery = true)
	Page<Permission> findAllPermissionCustom(Pageable pageable);

	/* Query Permissions by Name */
	@Query(value = "SELECT * FROM tb_permission WHERE unaccent(name) ilike unaccent('%' || ?1 || '%')", 
			nativeQuery = true)
	Page<Permission> findByNamePermissionCustom(String name, Pageable pageable);
	
	/* Query Permission by Id */
	@Query("select u from Permission u where u.id = ?1")
	Permission findByIdPermission(Long id);
}
