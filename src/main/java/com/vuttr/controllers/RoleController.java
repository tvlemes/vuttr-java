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

import com.vuttr.converters.RoleConverter;
import com.vuttr.dto.RoleDTO;
import com.vuttr.services.implementation.RoleServiceImp;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/roles")
public class RoleController {
	
	@Autowired
	RoleServiceImp service;
	
	@Autowired
	RoleConverter converter;
	
	/* List All Roles and Seach by Name */
	@ApiOperation(value = "Retorna uma lista com todas roles e consulta por nome. \n"
			+ "OBS.: Somente os usuários ADMIN e MANAGER tem permissão term permissão.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = ""
	    		+ "Retorna a lista com todas roles - Ex.: /api/role \n "
	    		+ "Consulta por nome da role - Ex.: /api/role?name=nome_role \n"
	    		+ "Retorna a página indicada - page=numero_pagina, e o número de registros - size=quantidade_registros, por página - Ex.: /api/role?page=0&size=3"), 
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@GetMapping(produces = "application/json")
	public ResponseEntity<?> getAllRole(
			@RequestParam(required = false) String name,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size){
							
			try {			
				
				Pageable paging = PageRequest.of(page, size);
		
				Page<RoleDTO> pageRoles;
				
				 if (name == null) {
					 pageRoles = service.findAllRoleCustom(paging);
				 }
				 else {
					 pageRoles = service.findByNameRoleCustom(name, paging);
				 }
				 
				 List<RoleDTO> roles = new ArrayList<RoleDTO>();				 				 
				 roles = pageRoles.getContent();
				 
				 Map<String, Object> response = new HashMap<>();
			     response.put("roles", roles);
			     response.put("currentPage", pageRoles.getNumber());
			     response.put("totalItems", pageRoles.getTotalElements());
			     response.put("totalPages", pageRoles.getTotalPages());
				 
				 if(!roles.isEmpty()) {
					 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				 }
				 return new ResponseEntity<String>("Não foi encontrada nenhuma Role!!!", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Ocorreu um erro no controller da requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}		
	}
	
	/* Get Role by Id */
	@ApiOperation(value = "Retorna uma role por ID. \n"
			+ "OBS.: Somente os usuários ADMIN e MANAGER tem permissão.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Consultando uma role por ID - Ex.: /api/roles/numero_id"),	    		
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> findByIdRole(@PathVariable Long id){
		RoleDTO role = service.findByIdRole(id);
		try {
			if(role != null) {
				return new ResponseEntity<RoleDTO>(role, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Role com o Código '" + id + "' não encontrada!!!",  HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro no controller da requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* Create Role */
	@ApiOperation(value = "Cria uma nova role. \n"
			+ "OBS.: Somente os usuários ADMIN e MANAGER tem permissão.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = ""
	    		+ "Criando uma nova role. Os dados da nova role devem ser passados por BODY em formato JSON\n"
	    		+ "Ex.: /api/roles verbo HTTP PUT\n"
	    		+ "JSON:\n"
	    		+ "{\n"
	    		+ "    \"nameRole\": \"ROLE_USER\",\n"
	    		+ "    \"description\": \"Função de Teste do Sistema\"\n"
	    		+ "}"),	    		
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@PostMapping(produces = "application/json")
	public ResponseEntity<?> create(@RequestBody @Valid RoleDTO role){
		RoleDTO entity = service.create(role);
		try {
			if(entity != null) {
				return new ResponseEntity<RoleDTO>(entity, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Não foi possível cadastrar a Role!!!",  HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro no controller da requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* Update Role */
	@ApiOperation(value = "Atualiza uma role. \n"
			+ "OBS.: Somente os usuários ADMIN e MANAGER tem permissão.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = ""
					+ "O ID da role deve ser passado via URL Ex.: localhost:8080/api/role/id_ferramenta\n"
		    		+ "e os dados da ferramenta devem ser passados por BODY em formato JSON\n"
					+ "{\n"
					+ "    \"id\": 4,\n"
					+ "    \"nameRole\": \"ROLE_TEST - atualizado\",\n"
					+ "    \"description\": \"Função de Teste do Sistema - atualizado\"\n"
					+ "}"),	    		
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid RoleDTO role){	
		try {
			role = service.update(id, role);
			if(role != null) {
				return new ResponseEntity<RoleDTO>(role, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Não foi possível atualizar a Role com o código " + id + "!!!",  HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Ocorreu um erro na requisição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* Delete Role */
	@ApiOperation(value = "Deleta uma role. \n"
			+ "OBS.: Somente os usuários ADMIN e MANAGER tem permissão.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Deleta uma role por ID. Ex.: localhost:8080/api/roles/id_função\\n"),	    		
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
