package com.vuttr.mock;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vuttr.models.Authorization;
import com.vuttr.models.Permission;
import com.vuttr.models.Role;
import com.vuttr.models.User;
import com.vuttr.models.enums.UserStatus;
import com.vuttr.repositories.AuthorizationRepository;
import com.vuttr.repositories.PermissionRepository;
import com.vuttr.repositories.RoleRepository;
import com.vuttr.repositories.UserRepository;

/* Keep this comment annotated so as not to insert data into the database */
//@Configuration
@SuppressWarnings("unused")
public class UserMock implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
//	@Autowired
//    private PermissionRepository permissionRepository;
//	@Autowired
//    private AuthorizationRepository authorizationRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		  /* Delete Data */
//        userRepository.deleteAll();
//		  roleRepository.deleteAll();
		
		  /* Insert Permissions */
//        Permission p1 = new Permission(null, "view_users", "Permissão para View Usuários");
//        Permission p2 = new Permission(null, "view_roles", "Permissão para View Funções");
//        Permission p3 = new Permission(null, "view_home", "Permissão para View Home");
//        permissionRepository.saveAll(Arrays.asList(p1, p2, p3));

          /* Insert Authorization */
//        Authorization a1 = new Authorization(null, "edit_users", "Edita Usuários");
//        Authorization a2 = new Authorization(null, "edit_roles", "Edita Funções do sistema");
//        Authorization a3 = new Authorization(null, "create_user", "Cria um novo Usuário");
//        Authorization a4 = new Authorization(null, "view_roles", "Visualiza uma função");
//        authorizationRepository.saveAll(Arrays.asList(a1, a2, a3, a4));
		
		/* Insert Roles Database */		
//		Role r1 = new Role(null, "ROLE_ADMIN", "Função de Administrador do Sistema");
//		Role r2 = new Role(null, "ROLE_MANAGER", "Função de Gerente do Sistema");
//		Role r3 = new Role(null, "ROLE_USER", "Função de Usuário do Sistema");
//		roleRepository.saveAll(Arrays.asList(r1, r2, r3));
		
		/* Insert Users Database */		
//		User u1 = new User(null, "Thiago Vilarinho Lemes", "thiago", passwordEncoder.encode("123"), UserStatus.ACTIVE, r1);
//		User u2 = new User(null, "Carina Lima", "carina", passwordEncoder.encode("123"), UserStatus.ACTIVE, r2);
//		User u3 = new User(null, "Spack Rella", "spack.rella", passwordEncoder.encode("123"), UserStatus.BLOCKED, r3);
//		User u4 = new User(null, "Rosimar Vilarinho", "rosimar.lemes", passwordEncoder.encode("123"), UserStatus.SUSPENDED, r3);
//		User u5 = new User(null, "Juscelino Lemes", "juscelino.lemes", passwordEncoder.encode("123"), UserStatus.BLOCKED, r3);
//		User u6 = new User(null, "Carol V.", "carol.lemes", passwordEncoder.encode("123"), UserStatus.BLOCKED, r2);
//		User u7 = new User(null, "Luíz Souza", "luiz.fulano", passwordEncoder.encode("123"), UserStatus.BLOCKED, r2);
//		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5, u6, u7));	
		
		  /* Created association Role and Permission */
//        r1.getPermissions().add(p1);
//        r1.getPermissions().add(p2);
//        r2.getPermissions().add(p1);
//        r3.getPermissions().add(p3);
//        roleRepository.saveAll(Arrays.asList(r1, r2, r3));
        
        
		  /* Created association Authorization User */
//        a1.getUsers().add(u1);
//        a2.getUsers().add(u1);
//        a2.getUsers().add(u7);
//        a3.getUsers().add(u1);
//        a4.getUsers().add(u2);
//        a4.getUsers().add(u3);
//        a3.getUsers().add(u4);
//        a4.getUsers().add(u5);
//        a4.getUsers().add(u6);
//        authorizationRepository.saveAll(Arrays.asList(a1, a2, a3, a4));
		
	}

}
