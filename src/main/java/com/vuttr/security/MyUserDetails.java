package com.vuttr.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vuttr.models.Authorization;
import com.vuttr.models.Permission;
import com.vuttr.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class MyUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private User user;

    public MyUserDetails(User user){
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

//         Extract list of permissions (name)
//        for(Permission p: user.getRole().getPermissions()){
//            GrantedAuthority authority = new SimpleGrantedAuthority(p.getName());
//            System.out.println(authority);
////            authorities.add(authority);
//        }

        // Extract list of authorizations (name)
//        for (Authorization a: user.getAuthorizations()){
//            GrantedAuthority authority = new SimpleGrantedAuthority(a.getName());
//            authorities.add(authority);
//        }

        // Extract list of roles (name)
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
        authorities.add(authority);

        return authorities;
    }

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
