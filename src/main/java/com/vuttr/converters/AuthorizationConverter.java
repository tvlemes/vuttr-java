package com.vuttr.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vuttr.dto.AuthorizationDTO;
import com.vuttr.models.Authorization;

@Component
public class AuthorizationConverter {

	/* Converter Models to DTO */
	public AuthorizationDTO entityToDto(Authorization authorization) {
		AuthorizationDTO dto = new AuthorizationDTO();
		dto.setId(authorization.getId());
		dto.setName(authorization.getName());
		dto.setDescription(authorization.getDescription());
//		dto.setUsers(authorization.getUsers());
		return dto;
	}
	
	public List<AuthorizationDTO> entityToDto(List<Authorization> authorization ){
		return	authorization.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	
	/* Converter DTO to Models */
	public Authorization dtoToEntity(AuthorizationDTO dto) {
		Authorization authorization = new Authorization();
		authorization.setId(dto.getId());
		authorization.setName(dto.getName());
		authorization.setDescription(dto.getDescription());	
//		authorization.setUsers(dto.getUsers());
		return authorization;
	}
	
	public List<Authorization> dtoToEntity(List<AuthorizationDTO> dto)
	{
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}
}
