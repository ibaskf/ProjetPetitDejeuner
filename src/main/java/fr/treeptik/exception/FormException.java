package fr.treeptik.exception;

public class FormException extends Exception {

	private static final long serialVersionUID = 1L;

	
	
	public FormException(String message) {
		super(message);
	}
	
	public FormException(String message, Throwable e) {
		super(message, e);
	}
}
