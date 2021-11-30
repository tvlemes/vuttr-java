package com.vuttr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vuttr.security.AuthRequest;
import com.vuttr.security.JwtUtil;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "Home Controller")
public class HomeController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwt;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	/* Home */
	@ApiOperation(value = "Retorna a página inicial.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a página inicial - Ex.: /api/home \n "),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@GetMapping(value = "/home", produces = "application/json" )
	public ResponseEntity<String> home() {
		System.out.print(passwordEncoder.encode("123"));
		String mensage = "Bem vindo a Vuttr!!!";
		return ResponseEntity.ok().body(mensage);
	}
	
	/* Access Denied */
	@ApiOperation(value = "Retorna cesso negado caso a página não seja acessível")
	@ApiResponses(value = {
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
		})
	@GetMapping(value = "/access-denied")
	public ResponseEntity<String> accessDenied() {
		String mensage = "Acesso negado!!!";
		return ResponseEntity.ok().body(mensage);
	}
	
	/* Logout */
	/* Home */
	@ApiOperation(value = "Faz Logout no sistema.")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Faz Logout no sistema - Ex.: /api/logout \n "),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@GetMapping(value = "/logout", produces = "application/json")
	public ResponseEntity<String> logout() {
		String mensage = "Logout efetuado com sucesso!!!";
		return ResponseEntity.ok().body(mensage);
	}

	
	/* Login */
	@ApiOperation(value = "Método para logar no sistema.")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = ""
		    		+ "Gerando token. Os dados da autenticação devem ser passados por BODY em formato JSON\n"
		    		+ "Ex.: /api/authenticate verbo HTTP POST\n"
		    		+ "JSON:\n"
		    		+ "{\n"
		    		+ "    \"email\": \"email_de_acesso\",\n"
		    		+ "    \"pass\": \"senha\"\n"
		    		+ "}"),	    		
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção."),
	})
	@PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPass())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return "Bearer " + jwt.generateToken(authRequest.getEmail());
    }
}
