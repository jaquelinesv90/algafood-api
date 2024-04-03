package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.domain.exception.BusinessException;
import com.algaworks.domain.exception.EntityInUseException;
import com.algaworks.domain.exception.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	

	// exceptionHandler implementado dentro do controlador só captura as exceptions geradas dentro desse controlador
		// ({StateNotFoundException.class} )posso passar mais de um dentro de chaves separado por virg
		@ExceptionHandler(EntityNotFoundException.class)
		public ResponseEntity<?> handleEntityNotFoundException(
				EntityNotFoundException ex, WebRequest request){
			
			return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
					HttpStatus.NOT_FOUND, request);
		}
		
		@ExceptionHandler(EntityInUseException.class)
		public ResponseEntity<?> handleEntityInUseExceptionException(
				EntityInUseException ex,  WebRequest request){
			
			return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
					HttpStatus.CONFLICT, request);

		}
		
		public ResponseEntity<?> handleBusinessException(
				BusinessException ex,  WebRequest request){
			
			return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
					HttpStatus.BAD_REQUEST, request);

		}
		
		protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,HttpHeaders headers,
				HttpStatus status,WebRequest request){
			
			if(body == null) {
				body = Problema.builder()
						.dateTime(LocalDateTime.now())
						.message(status.getReasonPhrase())
						.build();
			}else if(body instanceof String) {
				body = Problema.builder()
						.dateTime(LocalDateTime.now())
						.message(status.getReasonPhrase())
						.build();
			}
					
			return super.handleExceptionInternal(ex,body, headers, status, request);
			
		}
}
