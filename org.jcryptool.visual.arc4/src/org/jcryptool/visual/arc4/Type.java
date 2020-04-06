package org.jcryptool.visual.arc4;

public enum Type {
	
	/**
	 * Identifies a key data vector, as well as a wizard for key changing
	 */
	KEY,
	
	/**
	 * Identifies a ciphertext data vector
	 */
	ENC, 
	
	/**
	 * Identifies a plain text data vector, as well as a wizard for changing the plain text
	 */
	PLAIN, 
	
	/**
	 * Identifies a pseudo random data vector
	 */
	RAND;
}
