package com.vuttr.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.vuttr.models.Role;
import com.vuttr.models.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "O NOME é obrigatório!")
    private String name;
	
	@NotBlank(message = "O EMAIL é obrigatório!")
    private String email;
	
	@NotBlank(message = "A SENHA é obrigatória!")
    private String password;
	
    private UserStatus userStatus;     
	

	private Role role;
//	private Set<Authorization> authorizations = new HashSet<>();
	
}
