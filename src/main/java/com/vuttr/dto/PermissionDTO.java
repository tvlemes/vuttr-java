package com.vuttr.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.vuttr.models.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {
	
	private Long id;
	
	@NotBlank(message = "O campo NOME é obrigatório!")
	private String name;    
   
	@NotBlank(message = "O campo DESCRIÇÃO é obrigatório!")
    private String description;
	
	
	private Set<Role> roles = new HashSet<>();
	


}
