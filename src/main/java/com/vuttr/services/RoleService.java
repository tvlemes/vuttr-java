package com.vuttr.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vuttr.dto.RoleDTO;

@Service
public interface RoleService {
	
	/* Interface Get All Roles */
	public Page<RoleDTO> findAllRoleCustom(Pageable pageable);
	
	/* Interface Get Roles by Name */
	public Page<RoleDTO> findByNameRoleCustom(String name, Pageable pageable);
	
	/* Interface Get Roles by Id*/
	public RoleDTO findByIdRole(Long id);
	
	/* Interface Create Role */
	public RoleDTO create(RoleDTO user);
	
	/* Interface Update Role */
	public RoleDTO update(Long id, RoleDTO dto);
	
	/* Interface Delete Role */
	public String delete(Long id);
}
