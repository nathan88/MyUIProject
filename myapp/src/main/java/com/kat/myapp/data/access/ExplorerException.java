package com.kat.myapp.data.access;

/**
 * Generic exception class
 */
@SuppressWarnings("serial")
public class ExplorerException extends Exception {

	public ExplorerException() {
		super();
	}

	public ExplorerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ExplorerException(String arg0) {
		super(arg0);
	}

	public ExplorerException(Throwable arg0) {
		super(arg0);
	}

}
