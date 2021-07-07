package com.hithaui.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandle {
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage errorNotFound(NotFoundException ex, WebRequest re) {
		return new ErrorMessage(404, ex.getMessage());
	}
	
	@ExceptionHandler(DuplicateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage errorDuplicate (DuplicateException ex, WebRequest re) {
		return new ErrorMessage(400, ex.getMessage());
	}

}
