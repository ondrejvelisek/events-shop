package cz.muni.fi.eventsshop.exceptions;

/**
 * Thrown when method contract defines that bean MUST exists and caller MUST checked it before calling this method.
 */
public class BeanNotExistsException extends BadCallException {

	public BeanNotExistsException(String s) {
		super(s);
	}

	public BeanNotExistsException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public BeanNotExistsException(Throwable throwable) {
		super(throwable);
	}

}
