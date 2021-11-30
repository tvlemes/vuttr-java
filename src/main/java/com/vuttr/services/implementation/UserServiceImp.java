package com.vuttr.services.implementation;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vuttr.converters.UserConverter;
import com.vuttr.dto.UserDTO;
import com.vuttr.exceptions.DatabaseException;
import com.vuttr.exceptions.ResourceNotFoundException;
import com.vuttr.models.User;
import com.vuttr.repositories.UserRepository;
import com.vuttr.services.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	UserRepository repository;
	
	@Autowired
	UserConverter converter;

	/* Get All Users */
	@Override
	@Transactional(readOnly = true)
	public Page<UserDTO> findAllUserCustom(Pageable pageable) {
		Page<User> list = repository.findAllUserCustom(pageable);
		List<UserDTO> dtos = converter.entityToDto(list.getContent());
	    return new PageImpl<>(dtos, pageable, list.getTotalElements());
	}
	
	/* Get Users by Name */
	@Override
	@Transactional(readOnly = true)
	public Page<UserDTO> findByNameCustom(String name, Pageable pageable) {
		Page<User> list = repository.findByNameCustom(name, pageable);
	    List<UserDTO> dtos = converter.entityToDto(list.getContent());
	    return new PageImpl<>(dtos, pageable, list.getTotalElements());

	}

	/* Get User by Id */
	@Override
	@Transactional(readOnly = true)
	public UserDTO findByIdUser(Long id) {
		User user =  repository.findByIdUser(id);
		if(user == null) {
			return null;	
		}
		UserDTO dto = converter.entityToDto(user);
		return dto;	
					
	}

	/* Create User */
	@Override
	@Transactional
	public UserDTO create(UserDTO dto) {
		User user = new User(null, dto.getName(), dto.getEmail(), dto.getPassword(), dto.getUserStatus(),dto.getRole());
		user = repository.save(user);
		return converter.entityToDto(user);
	}
	
	/* Update User */
	@Override
	@SuppressWarnings("unused")
	@Transactional
	public UserDTO update(Long id, UserDTO dto){
		try {
			User entity = repository.getOne(id);            

            /** Call Method updateDate() */
            updateData(entity, dto);
            User user = new User(null, entity.getName(), entity.getEmail(), entity.getPassword(), entity.getUserStatus(),entity.getRole());
            user = repository.save(entity);
            return converter.entityToDto(entity);
		} catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
		
	}
	/* Method Update Data */
	private void updateData(User entity, UserDTO obj) {
        entity.setName(obj.getName());
        entity.setUserStatus(obj.getUserStatus());
        entity.setPassword(obj.getPassword());
        entity.setRole(obj.getRole());
	}

	/* Delete User */
	@Override
	@Transactional
	public String delete(@PathVariable Long id) {
		
		try {
			User user =  repository.findByIdUser(id);
			if(user == null) {
				return "Usuário com o código '" + id + "' não encontrado!!!";	
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
