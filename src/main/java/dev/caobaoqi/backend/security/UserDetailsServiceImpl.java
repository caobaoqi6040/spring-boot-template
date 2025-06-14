package dev.caobaoqi.backend.security;

import dev.caobaoqi.backend.model.Role;
import dev.caobaoqi.backend.model.User;
import dev.caobaoqi.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.getUserByEmail(email);
		Role role = user.getRole();
		List<SimpleGrantedAuthority> authorities = role.getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getCode()))
			.collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
		return org.springframework.security.core.userdetails.User
			.withUsername(user.getUsername())
			.password(user.getPassword())
			.accountLocked(!user.getEnable())
			.accountExpired(!user.getEnable())
			.credentialsExpired(!user.getEnable())
			.disabled(!user.getEnable())
			.authorities(authorities)
			.build();
	}

}
