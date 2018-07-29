
package com.kat.myapp.view;

import com.kat.myapp.data.access.AccessControlIF;
import com.kat.myapp.data.access.LoginListenerIF;
import com.kat.myapp.view.helper.CommonUiUtils;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class CreateAliasDialog extends ResizableWindow {

	private static final long serialVersionUID = 1L;

	private MyCarMainView explorer;
	private TextField connectionName;

	private TextField url;

	private TextField username;

	private PasswordField password;
	private com.kat.myapp.data.access.LoginListenerIF loginListener;
	private AccessControlIF accessControl;

	private Button login;

	public CreateAliasDialog(MyCarMainView explorer, AccessControlIF accessControl, LoginListenerIF loginListener) {
		super("Create new connection", CommonUiUtils.aliasIcon);
		this.explorer = explorer;
		this.accessControl = accessControl;
		this.loginListener = loginListener;
		// Set window position.
		this.setPositionX(200);
		this.setPositionY(50);

		VerticalLayout content = new VerticalLayout();
		content.setMargin(false);
		content.setSpacing(false);
		content.setSizeUndefined();

		this.center();

		content.addComponent(createLoginLayout());

		setContent(content);

	}

	protected Component createLoginLayout() {

		Component loginForm = buildLoginForm();

		return loginForm;

	}

	private Component buildLoginForm() {

		FormLayout loginFormLayout = new FormLayout();

		connectionName = new TextField("Connection");
		connectionName.setIcon(VaadinIcons.CONNECT);
		connectionName.setRequiredIndicatorVisible(true);
		connectionName.setDescription("Please enter valid Connection Name to IBM-i Server");
		loginFormLayout.addComponent(connectionName);
		loginFormLayout.setComponentAlignment(connectionName, Alignment.MIDDLE_CENTER);

		url = new TextField("URL");
		url.setIcon(VaadinIcons.LINK);
		url.setRequiredIndicatorVisible(true);
		url.setDescription("Please enter valid URL or IP address to IBM-i Server");
		loginFormLayout.addComponent(url);
		loginFormLayout.setComponentAlignment(url, Alignment.MIDDLE_CENTER);

		username = new TextField("User Name");
		username.setIcon(VaadinIcons.USER);
		username.setRequiredIndicatorVisible(true);
		username.setDescription("Please enter valid Username to access IBM-i Server");
		loginFormLayout.addComponent(username);
		loginFormLayout.setComponentAlignment(username, Alignment.MIDDLE_CENTER);

		password = new PasswordField("Password");
		password.setIcon(VaadinIcons.PASSWORD);
		password.setRequiredIndicatorVisible(true);
		password.setDescription("Please enter valid password to login to IBM-i Server");
		loginFormLayout.addComponent(password);
		loginFormLayout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);

		HorizontalLayout btnLayout = new HorizontalLayout();

		btnLayout.setStyleName("buttons");
		loginFormLayout.addComponent(btnLayout);
		login = new Button("Connect");
		btnLayout.addComponent(login);
		login.setDisableOnClick(true);
		login.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					login();
					UI.getCurrent().removeWindow(CreateAliasDialog.this);
				} finally {
					login.setEnabled(true);
				}
			}
		});
		login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		login.addStyleName(ValoTheme.BUTTON_FRIENDLY);

		// Component createFooter = createFooter();
		// loginFormLayout.addComponent(createFooter);
		// loginFormLayout.setComponentAlignment(createFooter,
		// Alignment.BOTTOM_CENTER);

		Button cancelBtn = new Button("Cancel", new CloseButtonListener());
		btnLayout.addComponent(cancelBtn);

		return loginFormLayout;
	}

	private void login() {

		if (accessControl.signIn(username.getValue(), password.getValue())) {
			loginListener.loginSuccessful();
		} else {
			showNotification(new Notification("Login failed", "Please check your username and password and try again.",
					Notification.Type.HUMANIZED_MESSAGE));
			connectionName.focus();
		}
	}

	private void showNotification(Notification notification) {
		// keep the notification visible a little while after moving the
		// mouse, or until clicked
		notification.setDelayMsec(2000);
		notification.show(Page.getCurrent());
	}

}
