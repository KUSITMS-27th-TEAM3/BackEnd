package com.kusitms.samsion.common.security.jwt;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

	private String email;

	public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String email) {
		super(authorities);
		this.email = email;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return email;
	}
}
