package com.kat.myapp.data.access;



/**
 * Default mock implementation of {@link AccessControlIF}. This implementation
 * accepts user name, password & url to login.
 */
public class AccessControl implements AccessControlIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4618120228917505788L;
	
	private boolean signIn;

	@Override
	public boolean signIn(String username, String password) {
		if (username == null || username.isEmpty() || password == null || password.isEmpty() )
			signIn = false;
		else
			signIn = true;

		return signIn;
	}

	@Override
	public boolean isUserSignedIn() {
		return signIn;
	}

}
