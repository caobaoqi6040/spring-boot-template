package dev.caobaoqi.backend.user.exception;

public class PermissionNotFoundException extends RuntimeException {
	public PermissionNotFoundException(String message) {
		super(message);
	}
}
