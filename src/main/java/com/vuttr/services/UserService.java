package com.vuttr.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vuttr.dto.UserDTO;

public interface UserService {
	
	/* Interface Get All Users */
	public Page<UserDTO> findAllUserCustom(Pageable pageable);
	
	/* Interface Get Users by Name */
	public Page<UserDTO> findByNameCustom(String name, Pageable pageable);
	
	/* Interface Get Users by Id*/
	public UserDTO findByIdUser(Long id);
	
	/* Interface Create User */
	public UserDTO create(UserDTO user);
	
	/* Interface Update User */
	public UserDTO update(Long id, UserDTO dto);
	
	/* Interface Delete User */
	public String delete(Long id);
}
