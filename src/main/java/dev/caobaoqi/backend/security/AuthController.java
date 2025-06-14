package dev.caobaoqi.backend.security;

import dev.caobaoqi.backend.user.domain.request.UserLoginRequestVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;

	@PostMapping("/sign-in")
	public ResponseEntity<String> signIn(@Valid @RequestBody UserLoginRequestVo vo) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
			vo.email(), vo.password()
		));
		return ResponseEntity.ok(authenticate.getPrincipal().toString());
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Void> handlerBadCredentialsException(BadCredentialsException ex) {
		log.info("badCredentialsException with {}", ex.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
