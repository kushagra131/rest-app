package com.vandelay.industries.restapp.exception;



public class ResourceNotFoundException extends RuntimeException {
	

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7361530237196173435L;
	
	
	public ResourceNotFoundException(String entity, String factoryId) {
		super("Could not find the Resource for " + entity + " Id: " + factoryId);
		
	}

}
