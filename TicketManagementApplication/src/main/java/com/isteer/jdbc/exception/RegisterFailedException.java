package com.isteer.jdbc.exception;

import com.isteer.jdbc.enums.TicketEnums;

public class RegisterFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RegisterFailedException(TicketEnums message) {
		super(message.getStatusMessage());

	}

}
