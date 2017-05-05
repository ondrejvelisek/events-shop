package cz.muni.fi.eventsshop.exceptions;

/**
 * General unchecked exception of system. It should be thrown if something went wrong
 * because caller of method did not followed contract of method
 * (passed incorrect parameters, did not prepare environment, called methods in wrong order, ...)
 */
public class BadCallException extends RuntimeException {

	public BadCallException(String s) {
		super(s);
	}

	public BadCallException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public BadCallException(Throwable throwable) {
		super(throwable);
	}

}
