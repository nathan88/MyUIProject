package com.kat.myapp.data.access;


import java.io.Serializable;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * UI content when the user is not logged in yet.
 */
public class LoginScreen extends CssLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField username;
	private TextField urlname;
	private PasswordField password;
	private Button login;
	private Button cancel;
	private LoginListenerIF loginListener;
	private AccessControlIF accessControl;
//	private TextField url;
//	private TextField connectionName;

	public LoginScreen(AccessControlIF accessControl, LoginListenerIF loginListener) {
		this.loginListener = loginListener;
		this.accessControl = accessControl;
		buildUI();
		username.focus();
	}

	private void buildUI() {
		addStyleName("login-screen");

		// login form, centered in the available part of the screen
		Component loginForm = buildLoginForm();

		// layout to center login form when there is sufficient screen space
		// - see the theme for how this is made responsive for various screen
		// sizes
		VerticalLayout centeringLayout = new VerticalLayout();
		centeringLayout.setMargin(false);
		centeringLayout.setSpacing(false);
		centeringLayout.setStyleName("centering-layout");
		centeringLayout.addComponent(loginForm);
		centeringLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

		// information text about logging in
		// CssLayout loginInformation = buildLoginInformation();

		addComponent(centeringLayout);
		// addComponent(loginInformation);

	}

	private Component buildLoginForm() {

		FormLayout loginFormLayout = new FormLayout();
		loginFormLayout.setSizeFull();

		// Image image = new Image(null, new
		// ThemeResource("img/myCar_Service_Logo.jpg"));
		// image.setStyleName("logo");
		// loginForm.addComponent(image);
		// loginForm.setComponentAlignment(image, Alignment.MIDDLE_CENTER);

//		Label content = new Label("										myCar: Car Service Management System");
//		content.setStyleName(ValoTheme.LABEL_H2);
//		loginFormLayout.addComponent(content);
//		loginFormLayout.setComponentAlignment(content, Alignment.TOP_LEFT);
		HorizontalLayout header = new HorizontalLayout();
//		Label desc = new Label("****************************");
//		desc.setStyleName(ValoTheme.LABEL_H2);
//		header.addComponent(desc);
		
		Image logo = new Image(null, new ThemeResource("img/myCar_Service_Logo.jpg"));
		header.addComponent(logo);

		loginFormLayout.addComponent(logo);
		loginFormLayout.setComponentAlignment(logo, Alignment.TOP_CENTER);

		username = new TextField("User Name");
		username.setIcon(VaadinIcons.USER);
		username.setRequiredIndicatorVisible(true);
		username.setDescription("Please enter valid Username to lonin");
		loginFormLayout.addComponent(username);
		loginFormLayout.setComponentAlignment(username, Alignment.MIDDLE_CENTER);

		password = new PasswordField("Password");
		password.setIcon(VaadinIcons.PASSWORD);
		password.setRequiredIndicatorVisible(true);
		password.setDescription("Please enter valid password to login");
		loginFormLayout.addComponent(password);
		loginFormLayout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);

		CssLayout btnCssLayout = new CssLayout();
		btnCssLayout.setStyleName("buttons");
		loginFormLayout.addComponent(btnCssLayout);
		login = new Button("Login");
		btnCssLayout.addComponent(login);
		login.setDisableOnClick(true);
		login.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					login();
				} finally {
					login.setEnabled(true);
				}
			}
		});
		login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		login.addStyleName(ValoTheme.BUTTON_FRIENDLY);

		Component createFooter = createFooter();
		loginFormLayout.addComponent(createFooter);
		loginFormLayout.setComponentAlignment(createFooter, Alignment.BOTTOM_CENTER);

		return loginFormLayout;
	}

	private CssLayout buildLoginInformation() {
		CssLayout loginInformation = new CssLayout();
		loginInformation.setStyleName("login-information");
		Image image = new Image(null, new ThemeResource("img/myCar_Service_Logo.jpg"));
		image.setStyleName("logo");
		loginInformation.addComponent(image);

		Label loginInfoText = new Label(
				"<h1>Login Information</h1>"
						+ "Log in to &quot;IBM-i Server&quot; using valid Username, Password and URL.",
				ContentMode.HTML);
		loginInfoText.setSizeFull();
		loginInformation.addComponent(loginInfoText);

		return loginInformation;
	}

	private Component createFooter() {
		// Create layout
		FormLayout footerLayout = new FormLayout();

		// Add application details at the left
		// Label appLabel = new Label();
		// appLabel.setContentMode(ContentMode.HTML);
		// appLabel.setValue("absMessage v0.1");
		// footerLayout.addComponent(appLabel);
		// footerLayout.setComponentAlignment(appLabel, Alignment.MIDDLE_LEFT);
		// appLabel.setWidth(null);

		// Add copyright notice in the center
		Label copyrightLabel = new Label();
		copyrightLabel.setContentMode(ContentMode.HTML);
		copyrightLabel.setValue(
				"&copy; Copyright " + 2018 + "&nbsp;" + "Software Engineering of America" + ". All Rights Reserved.");
		footerLayout.addComponent(copyrightLabel);
		footerLayout.setComponentAlignment(copyrightLabel, Alignment.MIDDLE_CENTER);



		return footerLayout;
	}

	private void login() {

		if (accessControl.signIn(username.getValue(), password.getValue())) {
			loginListener.loginSuccessful();
		} else {
			showNotification(new Notification("Login failed", "Please check your username and password and try again.",
					Notification.Type.HUMANIZED_MESSAGE));
			username.focus();
		}
	}

	private void showNotification(Notification notification) {
		// keep the notification visible a little while after moving the
		// mouse, or until clicked
		notification.setDelayMsec(2000);
		notification.show(Page.getCurrent());
	}

	public interface LoginListener extends Serializable {
		void loginSuccessful();
	}
}
