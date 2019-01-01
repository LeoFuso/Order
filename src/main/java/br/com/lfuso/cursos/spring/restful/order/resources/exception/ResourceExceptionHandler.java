package br.com.lfuso.cursos.spring.restful.order.resources.exception;

import br.com.lfuso.cursos.spring.restful.order.resources.error.MessageValidationError;
import br.com.lfuso.cursos.spring.restful.order.resources.error.ResourceError;
import br.com.lfuso.cursos.spring.restful.order.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ResourceError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {
		return ResourceError.of(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResourceError> dataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
		return ResourceError.of(HttpStatus.BAD_REQUEST, ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MessageValidationError> methodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
		return MessageValidationError.of(HttpStatus.BAD_REQUEST, ex.getBindingResult());
	}

}
