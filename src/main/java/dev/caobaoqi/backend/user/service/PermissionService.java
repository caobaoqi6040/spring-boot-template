package dev.caobaoqi.backend.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.caobaoqi.backend.model.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
	List<Permission> getPermissionsByRoleId(Long roleId);
}
