package cz.muni.fi.eventsshop.exceptions;

/**
 * General checked exception of system. It should be thrown if something went wrong
 * even though caller of method followed contract of method
 * (passed correct parameters, prepared environment, called methods in right order, ...)
 * Usually hard exceptions e.g. unexpected IO error, network connectivity problems, inconsistency of data, memory oveflow, ...
 */
public class InternalException extends Exception {

	public InternalException(String s) {
		super(s);
	}

	public InternalException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public InternalException(Throwable throwable) {
		super(throwable);
	}

}
