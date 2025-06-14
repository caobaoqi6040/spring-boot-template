package dev.caobaoqi.backend.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.caobaoqi.backend.model.Role;
import dev.caobaoqi.backend.user.exception.RoleNotFoundException;
import dev.caobaoqi.backend.user.mapper.RoleMapper;
import dev.caobaoqi.backend.user.service.PermissionService;
import dev.caobaoqi.backend.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	private final RoleMapper mapper;
	private final PermissionService permissionService;

	@Override
	public Role getRoleByUserId(Long userId) {
		Role role = Optional.ofNullable(mapper.selectRoleByUserId(userId))
			.orElseThrow(() -> new RoleNotFoundException(String.format("role not found with id %d", userId)));
		role.setPermissions(permissionService.getPermissionsByRoleId(role.getId()));
		return role;
	}

}
