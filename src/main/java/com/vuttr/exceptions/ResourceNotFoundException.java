package com.vuttr.exceptions;

public class ResourceNotFoundException extends  RuntimeException{
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id){
        super("o código '" + id + "' não foi encontrado!!!");
    }
}
