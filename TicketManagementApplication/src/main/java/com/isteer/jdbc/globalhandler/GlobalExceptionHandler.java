package com.isteer.jdbc.globalhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.isteer.jdbc.dto.StatusErrorDto;
import com.isteer.jdbc.enums.TicketEnums;
import com.isteer.jdbc.exception.RegisterFailedException;
@ControllerAdvice
// i used this annotation to display the data in json form directly. 
@ResponseBody
public class GlobalExceptionHandler {		
		@ExceptionHandler(Exception.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public StatusErrorDto Exception(Exception e) {
			StatusErrorDto invaildOperation = new StatusErrorDto();
			invaildOperation.setErrorCode(9888);
			invaildOperation.setErrorMessage(e.getMessage());
			e.printStackTrace();
			return invaildOperation;
		}
		
		@ExceptionHandler(com.isteer.jdbc.exception.PasswordException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public StatusErrorDto PasswordException(com.isteer.jdbc.exception.PasswordException e) {
			StatusErrorDto invaildOperation = new StatusErrorDto();
			invaildOperation.setErrorCode(TicketEnums.PASSWORD_INVAILD_EXCEPTION.getStatusCode());
			invaildOperation.setErrorMessage(TicketEnums.PASSWORD_INVAILD_EXCEPTION.getStatusMessage());
			return invaildOperation;
		}
		
		@ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public StatusErrorDto DuplicateKeyException(org.springframework.dao.DuplicateKeyException e) {
			StatusErrorDto invaildOperation = new StatusErrorDto();
			invaildOperation.setErrorCode(TicketEnums.DUPLICATE_URL_MESSAGE.getStatusCode());
			invaildOperation.setErrorMessage(TicketEnums.DUPLICATE_URL_MESSAGE.getStatusMessage());
			return invaildOperation;
		}
		
		@ExceptionHandler(org.springframework.security.authorization.AuthorizationDeniedException.class)
		@ResponseStatus(HttpStatus.FORBIDDEN)
		public StatusErrorDto AuthorizationDeniedException (org.springframework.security.authorization.AuthorizationDeniedException e) {
			StatusErrorDto invaildOperation = new StatusErrorDto();
			invaildOperation.setErrorCode(TicketEnums.AUTHORIZATION_DEINED.getStatusCode());
			invaildOperation.setErrorMessage(TicketEnums.AUTHORIZATION_DEINED.getStatusMessage());
			return invaildOperation;
		}
		@ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public StatusErrorDto EmptyResultDataAccessException (org.springframework.dao.EmptyResultDataAccessException e) {
			StatusErrorDto invaildOperation = new StatusErrorDto();
			invaildOperation.setErrorCode(TicketEnums.EMPTY_DATA_EXCEPTION.getStatusCode());
			invaildOperation.setErrorMessage(TicketEnums.EMPTY_DATA_EXCEPTION.getStatusMessage());
			return invaildOperation;
		}
		
		
//		@ExceptionHandler(RuntimeException.class)
//		@ResponseStatus(HttpStatus.BAD_REQUEST)
//		public StatusErrorDto RuntimeException (RuntimeException e) {
//			StatusErrorDto invaildOperation = new StatusErrorDto();
//			invaildOperation.setErrorCode(TicketEnums.RUNTIME_EXCEPTION.getStatusCode());
//			invaildOperation.setErrorMessage(TicketEnums.RUNTIME_EXCEPTION.getStatusMessage());
//			return invaildOperation;
//		}
		
		@ExceptionHandler(RegisterFailedException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public StatusErrorDto RegisterFailedException (RegisterFailedException e) {
			StatusErrorDto invaildOperation = new StatusErrorDto();
			invaildOperation.setErrorCode(TicketEnums.REGISTERATION_FAILED.getStatusCode());
			invaildOperation.setErrorMessage(TicketEnums.REGISTERATION_FAILED.getStatusMessage());
			return invaildOperation;
		}
		
		@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public StatusErrorDto MethodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException e) {
			StatusErrorDto invaildOperation = new StatusErrorDto();
			invaildOperation.setErrorCode(9345);
			invaildOperation.setErrorMessage(e.getBindingResult().getFieldError().getDefaultMessage());
			return invaildOperation;
		}
		
		
}
