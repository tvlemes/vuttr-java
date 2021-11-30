package com.vuttr.services.implementation;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;

import com.vuttr.converters.RoleConverter;
import com.vuttr.dto.RoleDTO;
import com.vuttr.exceptions.DatabaseException;
import com.vuttr.exceptions.ResourceNotFoundException;
import com.vuttr.models.Role;
import com.vuttr.repositories.RoleRepository;
import com.vuttr.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService{
	
	@Autowired
	RoleRepository repository;
	
	@Autowired
	RoleConverter converter;

	/* Get All Roles */
	@Override
	@Transactional(readOnly = true)
	public Page<RoleDTO> findAllRoleCustom(Pageable pageable) {
		Page<Role> list = repository.findAllRoleCustom(pageable);
		List<RoleDTO> dtos = converter.entityToDto(list.getContent());
	    return new PageImpl<>(dtos, pageable, list.getTotalElements());
	}

	/* Get Roles by Name */
	@Override
	@Transactional(readOnly = true)
	public Page<RoleDTO> findByNameRoleCustom(String name, Pageable pageable) {
		Page<Role> list = repository.findByNameRoleCustom(name, pageable);
	    List<RoleDTO> dtos = converter.entityToDto(list.getContent());
	    return new PageImpl<>(dtos, pageable, list.getTotalElements());
	}

	/* Get Role by Id */
	@Override
	@Transactional(readOnly = true)
	public RoleDTO findByIdRole(Long id) {
		Role role =  repository.findByIdRole(id);
		if(role == null) {
			return null;	
		}
		RoleDTO dto = converter.entityToDto(role);
		return dto;	
	}

	/* Create Role */
	@Override
	@Transactional
	public RoleDTO create(RoleDTO dto) {
		Role role = new Role(null, dto.getName(), dto.getDescription());
		role = repository.save(role);
		return converter.entityToDto(role);
	}
	
	/* Update Role */
	@Override
	@SuppressWarnings("unused")
	@Transactional
	public RoleDTO update(Long id, RoleDTO dto) {
		try {
			Role entity = repository.getOne(id);            

            /** Call Method updateDate() */
            updateData(entity, dto);
            Role role = new Role(null, entity.getName(), entity.getDescription());
            role = repository.save(entity);
            return converter.entityToDto(entity);
		} catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
	}
	/* Method Update Data */
	private void updateData(Role entity, RoleDTO obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getName());
	}

	/* Delete Role */
	@Override
	@Transactional
	public String delete(Long id) {
		try {
			Role role =  repository.findByIdRole(id);
			if(role == null) {
				return "Role com o código '" + id + "' não encontrada!!!";	
			}
			repository.deleteById(id);     
		}
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
		return "Operação realizada com sucesso!";
	}
}
