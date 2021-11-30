package com.vuttr.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vuttr.models.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserStatus userStatus;      
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
       
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "tb_user_authorization",
//            joinColumns = @JoinColumn(name = "user_id"), 
//            inverseJoinColumns = @JoinColumn(name = "authorization_id")) 
//    private Set<Authorization> authorizations = new HashSet<>();
    
//    public User(Long id, String name, String email, String password, UserStatus userStatus, Role role ) {
//    	this.id=id;
//    	this.name=name;
//    	this.email=email;
//    	this.password=password;
//    	this.userStatus=userStatus;
//    	this.role=role;
//    }
}
