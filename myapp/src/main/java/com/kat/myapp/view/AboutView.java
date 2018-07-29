package com.kat.myapp.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AboutView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8342411933872897826L;
	public static final String VIEW_NAME = "ABOUT";

	public AboutView() {
		CustomLayout aboutContent = new CustomLayout("aboutview");
		aboutContent.setStyleName("about-content");

		// you can add Vaadin components in predefined slots in the custom
		// layout
		aboutContent.addComponent(new Label(
				"myCar is an automobile repair service POS and management system."));

		setSizeFull();
		setMargin(false);
		setStyleName("about-view");
		addComponent(aboutContent);
		setComponentAlignment(aboutContent, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

}
