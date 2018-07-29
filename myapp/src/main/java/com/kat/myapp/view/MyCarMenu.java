package com.kat.myapp.view;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Responsive navigation menu presenting a list of available views to the user.
 */
public class MyCarMenu extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String VALO_MENUITEMS = "valo-menuitems";
	private static final String VALO_MENU_TOGGLE = "valo-menu-toggle";
	private static final String VALO_MENU_VISIBLE = "valo-menu-visible";
	private Navigator navigator;
	private Map<String, Button> viewButtons = new HashMap<String, Button>();

	private HorizontalLayout menuItemsLayout;

	public MyCarMenu(Navigator navigator) {
		this.navigator = navigator;
		// setPrimaryStyleName(ValoTheme.MENU_TITLE);
		// setStyleName(ValoTheme.MENUBAR_SMALL);

		setWidth("100%");
		setSpacing(true);
		setStyleName("backColorBlue");

		CssLayout logoContainer = new CssLayout();
		logoContainer.addStyleName("valo-content");
		logoContainer.setWidth(100, Unit.PERCENTAGE);
		addComponent(logoContainer);

		// button for toggling the visibility of the menu when on a small screen
		final Button showMenu = new Button("Menu", new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				if (getStyleName().contains(VALO_MENU_VISIBLE)) {
					removeStyleName(VALO_MENU_VISIBLE);
				} else {
					addStyleName(VALO_MENU_VISIBLE);
				}
			}
		});
		showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
		showMenu.addStyleName(VALO_MENU_TOGGLE);
		showMenu.setIcon(VaadinIcons.MENU);

		addComponent(showMenu);

		Image image = new Image(null, new ThemeResource("img/myCar_Service_Logo.jpg"));

		// container for the navigation buttons, which are added by addView()
		menuItemsLayout = new HorizontalLayout();
		menuItemsLayout.setPrimaryStyleName(VALO_MENUITEMS);
		logoContainer.addComponent(image);

		addComponent(menuItemsLayout);
		setExpandRatio(logoContainer, 1);

	}

	
	/**
	 * Register a pre-created view instance in the navigation menu and in the
	 * {@link Navigator}.
	 *
	 * @see Navigator#addView(String, View)
	 *
	 * @param view
	 *            view instance to register
	 * @param name
	 *            view name
	 * @param caption
	 *            view caption in the menu
	 * @param icon
	 *            view icon in the menu
	 */
	public void addView(View view, final String name, String caption, Resource icon) {
		navigator.addView(name, view);
		createViewButton(name, caption, icon);
	}

	/**
	 * Register a view in the navigation menu and in the {@link Navigator} based
	 * on a view class.
	 *
	 * @see Navigator#addView(String, Class)
	 *
	 * @param viewClass
	 *            class of the views to create
	 * @param name
	 *            view name
	 * @param caption
	 *            view caption in the menu
	 * @param icon
	 *            view icon in the menu
	 */
	public void addView(Class<? extends View> viewClass, final String name, String caption, Resource icon) {
		navigator.addView(name, viewClass);
		createViewButton(name, caption, icon);
	}

	private void createViewButton(final String name, String caption, Resource icon) {
		Button button = new Button(caption, new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo(name);

			}
		});
		button.setPrimaryStyleName(ValoTheme.MENU_ITEM);
		button.setIcon(icon);
		menuItemsLayout.addComponent(button);
		viewButtons.put(name, button);
	}

	/**
	 * Highlights a view navigation button as the currently active view in the
	 * menu. This method does not perform the actual navigation.
	 *
	 * @param viewName
	 *            the name of the view to show as active
	 */
	public void setActiveView(String viewName) {
		for (Button button : viewButtons.values()) {
			button.removeStyleName("selected");
		}
		Button selected = viewButtons.get(viewName);
		if (selected != null) {
			selected.addStyleName("selected");
		}
		removeStyleName(VALO_MENU_VISIBLE);
	}
}
