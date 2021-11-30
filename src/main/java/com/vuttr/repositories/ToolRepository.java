package com.vuttr.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vuttr.models.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {


	/* Query All Tools */
	@Query(value = "SELECT * FROM tb_tool", 
			nativeQuery = true)
	Page<Tool> findAllToolCustom(Pageable pageable);

	/* Query Tools by Tag */
	@Query(value = "SELECT * FROM tb_tool WHERE unaccent(tags) ilike unaccent('%' || ?1 || '%')", 
			nativeQuery = true)
	Page<Tool> findByTagCustom(String tag, Pageable pageable);
	
	/* Query Tools by Id */
	@Query("select u from Tool u where u.id = ?1")
	Tool findByIdTool(Long id);

}
