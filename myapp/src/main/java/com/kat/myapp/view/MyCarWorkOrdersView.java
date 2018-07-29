package com.kat.myapp.view;


import com.kat.myapp.backend.database.WorkOrder;
import com.kat.myapp.form.MyCarCustomerForm;
import com.kat.myapp.view.helper.CommonUiUtils;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
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
public class MyCarWorkOrdersView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1818416422444396412L;
	public static final String VIEW_NAME = "WORK ORDER";
	public static final String TAB_LOOKUP       = "WORK ORDER LOOKUP";
	public static final String TAB_WORKORDER    = "WORK ORDER";
	public static final String TAB_MYWORKORDERS = "MY WORK ORDERS";
	



	public MyCarWorkOrdersView() {
		setSizeFull();
	
//		addComponents(createCustomersTab());
		
		VerticalLayout content = new VerticalLayout();
		content.setSpacing(false);
		content.setMargin(false);
		content.setSizeFull();
		
		CssLayout tabLayout = new CssLayout();
		tabLayout.setSizeFull();
		content.addComponent(tabLayout);

		TabSheet tabSheet = CommonUiUtils.createTabSheet();
		tabSheet.setSizeFull();
		tabLayout.addComponent(tabSheet);

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

				if (caption.equals(TAB_WORKORDER)) {
//					loadFilterHistoryTab();
				}

				if (caption.equals(TAB_LOOKUP)) {
//					loadInstructionTab();
				}


			}
		});

		final TabSheet.Tab lookup = tabSheet.addTab(createLookupTab(), TAB_LOOKUP);

		final TabSheet.Tab workOrder = tabSheet.addTab(createWorkOrderTab(), TAB_WORKORDER);
		
		final TabSheet.Tab myWorkOrder = tabSheet.addTab(createWorkOrderTab(), TAB_MYWORKORDERS);

	
		addComponents(tabLayout);
		

	}
	
	private Component createWorkOrderTab() {


		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		
		GridLayout gridLayout = new GridLayout(2, 2);
		
//		MyCarCustomerForm customerForm = new MyCarCustomerForm();
//		gridLayout.addComponent(customerForm, 0, 0);
		
		
		gridLayout.setSpacing(true);
		gridLayout.setMargin(true);
		
		layout.addComponent(gridLayout);

		return layout;
	}



	private Component createLookupTab() {

		TextField byName;
		TextField byContactNumber;
		TextField byPlate;
		
		VerticalLayout main = new VerticalLayout();
		main.setSizeFull();
		
		GridLayout layout = new GridLayout(4, 3);
		
		// Row 1
		Label label = new Label("By name");
		layout.addComponent(label, 0, 0);
		byName = new TextField();
//		firstName.setReadOnly(true);
//		firstName.setSizeFull();
		layout.addComponent(byName, 1, 0);

		// Row 2
		label = new Label("By contact number");
		layout.addComponent(label, 0, 1);
		byContactNumber = new TextField();
//		lastName.setReadOnly(true);
//		lastName.setSizeFull();
		layout.addComponent(byContactNumber, 1, 1);
		
		// Row 3
		label = new Label("By license plate");
		layout.addComponent(label, 0, 2);
		byPlate = new TextField();
//		cellNumber.setReadOnly(true);
//		cellNumber.setSizeFull();
		layout.addComponent(byPlate, 1, 2);

		
		Button go = new Button("Go");
		layout.addComponent(go, 3, 2);
		
		layout.setSpacing(true);
		layout.setMargin(true);
		
		main.addComponent(layout);

		return main;
	}
	

}
