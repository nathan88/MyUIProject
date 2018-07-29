package com.kat.myapp.view;


import com.kat.myapp.MyUI;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MyCarMainScreen extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyCarMenu menu;

	public MyCarMainScreen(MyUI ui) {

		setSpacing(false);
		setStyleName("main-screen");
		setMargin(false);

		CssLayout header = new CssLayout();
		header.addStyleName("valo-content");
		header.setWidth(100, Unit.PERCENTAGE);

		HorizontalLayout viewContainer = new HorizontalLayout();
		viewContainer.addStyleName("valo-content");
		viewContainer.setWidth(100, Unit.PERCENTAGE);
		viewContainer.setSizeFull();

		final Navigator navigator = new Navigator(ui, viewContainer);
		navigator.setErrorView(ErrorView.class);
		menu = new MyCarMenu(navigator);
		menu.addView(new MyCarCustomersView(), MyCarCustomersView.VIEW_NAME, MyCarCustomersView.VIEW_NAME, VaadinIcons.USER);
		menu.addView(new MyCarWorkOrdersView(), MyCarWorkOrdersView.VIEW_NAME, MyCarWorkOrdersView.VIEW_NAME, VaadinIcons.TASKS);
		menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME, VaadinIcons.INFO_CIRCLE);

		navigator.addViewChangeListener(viewChangeListener);
		navigator.navigateTo(MyCarMainView.VIEW_NAME);

		header.addComponent(menu);

		addComponent(header);
		addComponent(viewContainer);

		setExpandRatio(header, 0.2f);
		setExpandRatio(viewContainer, 2.0f);
		setSizeFull();
	}

	// notify the view menu about view changes so that it can display which view
	// is currently active
	ViewChangeListener viewChangeListener = new ViewChangeListener() {

		@Override
		public boolean beforeViewChange(ViewChangeEvent event) {
			return true;
		}

		@Override
		public void afterViewChange(ViewChangeEvent event) {
			menu.setActiveView(event.getViewName());
		}

	};

}
