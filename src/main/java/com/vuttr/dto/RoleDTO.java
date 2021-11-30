package com.vuttr.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "O campo NOME é obrigatório!")
	private String name;
	
	@NotBlank(message = "O campo DESCRIÇÃO é obrigatório!")
	private String description;
	
	
//	private Set<User> users = new HashSet<>();
//	private Set<Permission> permissions = new HashSet<>();
}
