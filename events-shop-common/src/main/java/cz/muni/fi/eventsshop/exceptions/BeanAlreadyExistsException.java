package cz.muni.fi.eventsshop.exceptions;

/**
 * Thrown when method contract defines uniqueness of some bean field and caller MUST checked freedom  of it before calling this method.
 */
public class BeanAlreadyExistsException extends BadCallException {

	public BeanAlreadyExistsException(String s) {
		super(s);
	}

	public BeanAlreadyExistsException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public BeanAlreadyExistsException(Throwable throwable) {
		super(throwable);
	}

}
