package com.isteer.jdbc.exception;

import com.isteer.jdbc.enums.TicketEnums;

public class PasswordException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PasswordException(TicketEnums message) {
		super( message.getStatusMessage());

		
	
	}

}
