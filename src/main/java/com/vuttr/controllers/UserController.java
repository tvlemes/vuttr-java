package com.vuttr.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vuttr.converters.UserConverter;
import com.vuttr.dto.UserDTO;
import com.vuttr.services.implementation.UserServiceImp;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
	
	@Autowired
	UserServiceImp service;
	
	@Autowired
	UserConverter converter;
	
	/* List All Users and Seach by Name */
	@ApiOperation(value = "Retorna uma lista com todos usuário e consulta por nome. \n"
			+ "OBS.: Somente o usuário ADMIN tem permissão.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = ""
	    		+ "Retorna a lista com todos usuário - Ex.: /api/users \n "
	    		+ "Consulta por nome de usuário - Ex.: /api/users?name=nome_usuário \n"
	    		+ "Retorna a página indicada - page=numero_pagina, e o número de registros - size=quantidade_registros, por página - Ex.: /api/users?page=0&size=3"), 
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@GetMapping(produces = "application/json")
	public ResponseEntity<?> getAllUser(
			@RequestParam(required = false) String name,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size){
							
			try {			
				
				Pageable paging = PageRequest.of(page, size);
		
				Page<UserDTO> pageUsers;
				
				 if (name == null) {
					 pageUsers = service.findAllUserCustom(paging);
				 }
				 else {
					 pageUsers = service.findByNameCustom(name, paging);
				 }
				 
				 List<UserDTO> users = new ArrayList<UserDTO>();				 				 
				 users = pageUsers.getContent();
				 
				 Map<String, Object> response = new HashMap<>();
			     response.put("users", users);
			     response.put("currentPage", pageUsers.getNumber());
			     response.put("totalItems", pageUsers.getTotalElements());
			     response.put("totalPages", pageUsers.getTotalPages());
				 
				 if(!users.isEmpty()) {
					 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				 }
				 return new ResponseEntity<String>("Não foi encontrado nenhum usuário!!!", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Ocorreu um erro no controller da requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}		
	}
	
	/* Get User by Id */
	@ApiOperation(value = "Retorna um usuário por ID. \n"
			+ "OBS.: Somente o usuário ADMIN tem permissão.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Consultando um usuário por ID - Ex.: /api/users/numero_id"),	    		
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> findByIdUser(@PathVariable Long id){
		UserDTO user = service.findByIdUser(id);
		try {
			if(user != null) {
				return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Usuario com o Código '" + id + "' não encontrado!!!",  HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro no controller da requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* Create User */
	@ApiOperation(value = "Cria um novo usuário. \n"
			+ "OBS.: Somente o usuário ADMIN tem permissão.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = ""
	    		+ "Criando um novo usuário. Os dados do novo usuário devem ser passados por BODY em formato JSON\n"
	    		+ "Ex.: /api/users verbo HTTP POST"
	    		+ "JSON:\n"
	    		+ "{\n"
	    		+ "    \"name\": \"Fulano de Tal\",\n"
	    		+ "    \"email\": \"fulanodetal@gmail.com\",\n"
	    		+ "    \"password\": \"123\",\n"
	    		+ "    \"userStatus\": 1,\n"
	    		+ "    \"role\": {\n"
	    		+ "        \"id\": 1,\n"
	    		+ "        \"nameRole\": \"ROLE_ADMIN\",\n"
	    		+ "        \"description\": \"Função de Administrador do Sistema\"\n"
	    		+ "        }\n"
	    		+ "}"),	    		
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@PostMapping(produces = "application/json")
	public ResponseEntity<?> create(@RequestBody @Valid UserDTO user){
		UserDTO entity = service.create(user);
		try {
			if(entity != null) {
				return new ResponseEntity<UserDTO>(entity, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Não foi possível cadastrar o usuário",  HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro no controller da requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* Update User */
	@ApiOperation(value = "Atualiza um usuário. \n"
			+ "Somente o usuário ADMIN tem permissão.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = ""
	    		+ "O ID do usuário deve ser passado via URL Ex.: localhost:8080/api/users/id_usuario\n"
	    		+ "e os dados do usuário devem ser passados por BODY em formato JSON\n"
	    		+ "Ex.: /api/users verbo HTTP PUT"
	    		+ "{\n"
	    		+ "    \"name\": \"Fulano de Tal\",\n"
	    		+ "    \"email\": \"fulanodetal@gmail.com\",\n"
	    		+ "    \"password\": \"123\",\n"
	    		+ "    \"userStatus\": 1,\n"
	    		+ "    \"role\": {\n"
	    		+ "        \"id\": 1,\n"
	    		+ "        \"nameRole\": \"ROLE_ADMIN\",\n"
	    		+ "        \"description\": \"Função de Administrador do Sistema\"\n"
	    		+ "        }\n"
	    		+ "}"),	    		
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid UserDTO user){
		try {
			user = service.update(id, user);
			if(user != null) {
				return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Não foi possível atualizar a Role com o código '" + id + "'!!!",  HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro na requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* Delete User */	
	@ApiOperation(value = "Deleta um usuário. \n"
			+ "OBS.: Somente o usuário ADMIN tem permissão.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Deleta um usuário por ID. Ex.: localhost:8080/api/users/id_usuario \n"),	    		
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		String msg = service.delete(id);
		try {
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro no controller da requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/** Response BAD REQUEST */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
