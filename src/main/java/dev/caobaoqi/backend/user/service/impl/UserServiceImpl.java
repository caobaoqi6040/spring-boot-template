package dev.caobaoqi.backend.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.caobaoqi.backend.model.User;
import dev.caobaoqi.backend.user.mapper.UserMapper;
import dev.caobaoqi.backend.user.service.RoleService;
import dev.caobaoqi.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	private final RoleService roleService;

	@Override
	public User getUserByEmail(String email) {
		User user = this.getOneOpt(Wrappers.<User>query().eq("email", email))
			.orElseThrow(() -> new UsernameNotFoundException(String.format("user not found with email %s", email)));
		user.setRole(roleService.getRoleByUserId(user.getId()));
		return user;
	}

}
