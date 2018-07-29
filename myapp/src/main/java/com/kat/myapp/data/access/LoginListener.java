package com.kat.myapp.data.access;

import com.kat.myapp.MyUI;

public class LoginListener implements LoginListenerIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2215470388366767443L;

	@Override
	public void loginSuccessful() {
		MyUI.get().showMainView();

	}

}
