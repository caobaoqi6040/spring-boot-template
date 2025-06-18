package dev.caobaoqi.backend.user.controller;

import dev.caobaoqi.backend.model.User;
import dev.caobaoqi.backend.user.domain.UserStruct;
import dev.caobaoqi.backend.user.domain.response.UserInfoVo;
import dev.caobaoqi.backend.user.exception.PermissionNotFoundException;
import dev.caobaoqi.backend.user.exception.RoleNotFoundException;
import dev.caobaoqi.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserStruct userStruct = Mappers.getMapper(UserStruct.class);

    @GetMapping
    public ResponseEntity<List<UserInfoVo>> list() {
        List<UserInfoVo> userInfoVos = userService.list().stream()
                .map(userStruct::toUserInfoVo)
                .toList();
        return ResponseEntity.ok(userInfoVos);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserInfoVo> getByEmail(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(userStruct.toUserInfoVo(user));
    }

    @ExceptionHandler({UsernameNotFoundException.class, RoleNotFoundException.class, PermissionNotFoundException.class})
    public ResponseEntity<String> handleException(Exception e) {
        log.info("UserController.handleException", e);
        return ResponseEntity.notFound().build();
    }

}
