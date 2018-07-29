package com.kat.myapp.view;


import com.kat.myapp.view.helper.CommonUiUtils;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;

/**
 * A view for performing create-read-update-delete operations on products.
 *
 * See also {@link ABSMessageLogic} for fetching the data, the actual CRUD
 * operations and controlling the view based on events from outside.
 */
public class MyCarMainView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1818416422444396412L;
	public static final String VIEW_NAME = "CUSTOMER";
	public static final String TAB_CUSTOMERS  = "CUSTOMERS";
	public static final String TAB_CUSTOMER   = "CUSTOMER";
	public static final String TAB_LOOKUP     = "LOOKUP";
	public static final String TAB_WORKORDERS = "WORKORDERS";
	public static final String TAB_MYWORKORDERS = "MY WORKORDERS";
	


	public MyCarMainView() {
		setSizeFull();
	
//		addComponents(createCustomersTab());
		
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(false);
		content.setMargin(false);
		content.setSizeFull();
		
		MyCarCustomersView customerView = new MyCarCustomersView();
		
		CssLayout tabLayout = new CssLayout();
		tabLayout.setSizeFull();
		content.addComponent(tabLayout);

		TabSheet tabSheet = CommonUiUtils.createTabSheet();
		tabSheet.setSizeFull();
		tabLayout.addComponent(tabSheet);
//		tabLayout.addComponent(customerView);

		tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6087481715943432407L;

			public void selectedTabChange(SelectedTabChangeEvent event) {
				// Find the tabsheet
				TabSheet tabsheet = event.getTabSheet();

				Component selectedTab = tabsheet.getSelectedTab();

				// Get the tab caption from the tab object
				String caption = tabsheet.getTab(selectedTab).getCaption();

				if (caption.equals(TAB_CUSTOMERS)) {
//					loadFilterHistoryTab();
				}

				if (caption.equals(TAB_WORKORDERS)) {
//					loadInstructionTab();
				}


			}
		});

		final TabSheet.Tab customers = tabSheet.addTab(customerView, TAB_CUSTOMERS);

		final TabSheet.Tab workOrders = tabSheet.addTab(createWorkOrdersTab(), TAB_WORKORDERS);

//		addComponents(customerView);
		addComponents(tabLayout);
		

	}
	private Component createWorkOrdersTab() {
		VerticalLayout main = new VerticalLayout();
		main.setSizeFull();
		
		return main;
		
	}
//	private Component createCustomersTab() {
//
//
//		VerticalLayout main = new VerticalLayout();
//		main.setSizeFull();
//		
//		GridLayout layout = new GridLayout(6, 5);
//		
//		// Row 1
//		Label label = new Label("First name:");
//		layout.addComponent(label, 0, 0);
//		firstName = new TextField();
////		firstName.setReadOnly(true);
////		firstName.setSizeFull();
//		layout.addComponent(firstName, 1, 0);
//
//		
//		label = new Label("Last name:");
//		layout.addComponent(label, 3, 0, 4, 0);
//		lastName = new TextField();
////		lastName.setReadOnly(true);
////		lastName.setSizeFull();
//		layout.addComponent(lastName, 5, 0);
//		
//		// Row 2
//		label = new Label("Cell number:");
//		layout.addComponent(label, 0, 1);
//		cellNumber = new TextField();
////		cellNumber.setReadOnly(true);
////		cellNumber.setSizeFull();
//		layout.addComponent(cellNumber, 1, 1);
//
//		
//		label = new Label("Work number:");
//		layout.addComponent(label, 3, 1, 4, 1);
//		workNumber = new TextField();
////		workNumber.setReadOnly(true);
////		workNumber.setSizeFull();
//		layout.addComponent(workNumber, 5, 1);
//		
//		// Row 3
//		label = new Label("Email:");
//		layout.addComponent(label, 0, 2);
//		email = new TextField();
////		email.setReadOnly(true);
////		email.setSizeFull();
//		layout.addComponent(email, 1, 2);
//
//		
//		label = new Label("Home number:");
//		layout.addComponent(label, 3, 2, 4, 2);
//		homeNumber = new TextField();
////		homeNumber.setReadOnly(true);
////		homeNumber.setSizeFull();
//		layout.addComponent(homeNumber, 5, 2);
//		
//		// Row 4
//		label = new Label("Address:");
//		layout.addComponent(label, 0, 3);
//		address = new TextField();
////		address.setReadOnly(true);
//		address.setSizeFull();
//		layout.addComponent(address, 1, 3, 5, 3);
//
//		// Row 5
//		label = new Label("City:");
//		layout.addComponent(label, 0, 4);
//		city = new TextField();
////		address.setReadOnly(true);
////		address.setSizeFull();
//		layout.addComponent(city, 1, 4);
//		
//		label = new Label("State:");
//		layout.addComponent(label, 2, 4);
//		state = new TextField();
//		state.setWidth(30, Unit.PIXELS);
////		state.setReadOnly(true);
////		state.setSizeFull();
//		layout.addComponent(state, 3, 4);
//		
//		label = new Label("Country:");
//		layout.addComponent(label, 4, 4);
//		country = new TextField();
////		country.setReadOnly(true);
////		country.setSizeFull();
//		layout.addComponent(country, 5, 4);
//		
//		layout.setSpacing(true);
//		layout.setMargin(true);
//		
//		main.addComponent(layout);
//
//		return main;
//	}


}
