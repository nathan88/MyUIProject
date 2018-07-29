package com.kat.myapp;

import javax.servlet.annotation.WebServlet;

import com.kat.myapp.data.access.AccessControlIF;
import com.google.common.eventbus.EventBus;
import com.kat.myapp.data.access.AccessControl;
import com.kat.myapp.data.access.LoginListener;
import com.kat.myapp.data.access.LoginScreen;
import com.kat.myapp.view.MyCarMainScreen;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Main UI class of the application that shows either the login screen or the
 * main view of the application depending on whether a user is signed in.
 *
 * The @Viewport annotation configures the viewport meta tags appropriately on
 * mobile devices. Instead of device based scaling (default), using responsive
 * layouts.
 */
@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("abstheme")
//@Widgetset("com.kat.myapp.AbsAppWidgetset")
public class MyUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccessControlIF accessControl = new AccessControl();
	private EventBus eventBus = new EventBus();
	

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Responsive.makeResponsive(this);
		setLocale(vaadinRequest.getLocale());
		getPage().setTitle("myCar");
		if (!accessControl.isUserSignedIn()) {
			LoginScreen loginScreen = new LoginScreen(accessControl, new LoginListener() );
			setContent(loginScreen);
		} else {
			showMainView();
		}
		
//		showMainView();
	}

	public void showMainView() {
		addStyleName(ValoTheme.UI_WITH_MENU);
		setContent(new MyCarMainScreen(MyUI.this));
		getNavigator().navigateTo(getNavigator().getState());
	}
	
//	protected void showMainView(){
//		// init general look and feel
//		addStyleName(ValoTheme.UI_WITH_MENU);
//	    mainLayout = new MainLayout();
//	    setContent(mainLayout);
//	}

	public static MyUI get() {
		return (MyUI) UI.getCurrent();
	}

	public AccessControlIF getAccessControl() {
		return accessControl;
	}

	@WebServlet(urlPatterns = "/*", name = "AbsUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class AbsUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}


	
}
