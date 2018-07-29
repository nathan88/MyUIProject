package com.kat.myapp.data.access;

import java.io.Serializable;

/**
 * Simple interface for authentication and authorization checks.
 */
public interface AccessControlIF extends Serializable {

	public boolean signIn(String username, String password);

	public boolean isUserSignedIn();

}
