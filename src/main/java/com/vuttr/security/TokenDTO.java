package com.vuttr.security;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String type;
	private String token;
}
