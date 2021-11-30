package com.vuttr.converters;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vuttr.dto.PermissionDTO;
import com.vuttr.models.Permission;

@Component
public class PermissionConverter implements Serializable{
	private static final long serialVersionUID = 1L;
	

	/* Converter Models to DTO */
	public PermissionDTO entityToDto(Permission permission) {
		PermissionDTO dto = new PermissionDTO();
		dto.setId(permission.getId());
		dto.setName(permission.getName());
		dto.setDescription(permission.getDescription());
//		dto.setRoles(permission.getRoles());
		return dto;
	}
		
	public List<PermissionDTO> entityToDto(List<Permission> permission ){
		return	permission.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	
	public List<PermissionDTO> entityToDtoPage(List<Permission> permission ){
		return	permission.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	
	/* Converter DTO to Models */
	public Permission dtoToEntity(PermissionDTO dto) {
		Permission permission = new Permission();
		permission.setId(dto.getId());
		permission.setName(dto.getName());
		permission.setDescription(dto.getDescription());
//		permission.setRoles(dto.getRoles());
		return permission;
	}
	
	public List<Permission> dtoToEntity(List<PermissionDTO> dto)
	{
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
}
