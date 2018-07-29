package com.kat.myapp.view;


import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.kat.myapp.MyUI;
import com.kat.myapp.backend.database.Customer;
import com.kat.myapp.backend.database.Vehicle;
import com.kat.myapp.backend.exception.ServiceException;
import com.kat.myapp.event.CustomerGridChangeEventHandler;
import com.kat.myapp.event.CustomerGridChangedEvent;
import com.kat.myapp.event.VehicleGridChangeEventHandler;
import com.kat.myapp.event.VehicleGridChangedEvent;
import com.kat.myapp.form.MyCarCustomerForm;
import com.kat.myapp.form.MyCarCustomerLookupForm;
import com.kat.myapp.grid.MyCarCustomerGrid;
import com.kat.myapp.grid.MyCarVehicleGrid;
import com.kat.myapp.view.helper.CommonUiUtils;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid.ItemClick;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.components.grid.ItemClickListener;

/**
 * A view for performing create-read-update-delete operations on products.
 *
 * See also {@link ABSMessageLogic} for fetching the data, the actual CRUD
 * operations and controlling the view based on events from outside.
 */
public class MyCarCustomersView extends VerticalLayout implements View {

	final static Logger logger = Logger.getLogger(MyCarCustomersView.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -1818416422444396412L;
	public static final String VIEW_NAME = "CUSTOMER";
	public static final String TAB_CUSTOMERS  = "CUSTOMERS";
	public static final String TAB_CUSTOMER   = "CUSTOMER PROFILE";
	public static final String TAB_LOOKUP     = "CUSTOMER LOOKUP";
	
	MyCarCustomerGrid customerGrid ;
	MyCarVehicleGrid  vehicleGrid ;




	public MyCarCustomersView() {
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

				if (caption.equals(TAB_CUSTOMER)) {
//					loadFilterHistoryTab();
				}

				if (caption.equals(TAB_LOOKUP)) {
//					loadInstructionTab();
				}


			}
		});

		final TabSheet.Tab lookup = tabSheet.addTab(createLookupTab(), TAB_LOOKUP);

		final TabSheet.Tab customers = tabSheet.addTab(createCustomerTab(), TAB_CUSTOMER);

	
		addComponents(tabLayout);
		

	}
	private Component createLookupTab() {
		
		
		VerticalSplitPanel layout = new VerticalSplitPanel();
		layout.setSplitPosition(50);
		layout.setSizeFull();
		
		MyCarCustomerLookupForm lookupForm = new MyCarCustomerLookupForm();
		layout.setFirstComponent(lookupForm);
		layout.setSecondComponent(createLookupBottom());
		

		return layout;

	}
	
	
	private Component createCustomerTab() {


		VerticalSplitPanel layout = new VerticalSplitPanel();
		layout.addStyleName("abssub");
		layout.setSplitPosition(50);
		layout.setSizeFull();
		

		
		MyCarCustomerForm customerForm = new MyCarCustomerForm();
		layout.setFirstComponent(customerForm);
//		layout.setSecondComponent(createCustomerGrid(null));
		

		return layout;
	}
	
	@SuppressWarnings("unchecked")
	private VerticalLayout createCustomerGrid(Customer customer) {


		customerGrid = new MyCarCustomerGrid();
		try {
			if ( customer != null )
			    customerGrid.setItems(Customer.getCustomers());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		customerGrid.setColumnReorderingAllowed(true);
		customerGrid.setHeaderVisible(true);
		customerGrid.setResponsive(true);
		customerGrid.setRowHeight(25);

		customerGrid.addItemClickListener(new ItemClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1772629545957434653L;

			@Override
			public void itemClick(ItemClick event) {

				Customer selectedCustomer = (Customer) event.getItem();
				try {
					logger.debug("Extract first customer's vehicle(s) in list"); 
					List<Vehicle> vehicles = Vehicle.getVehiclesByOwner(selectedCustomer.getId());
					logger.debug(vehicles);
//					if ( vehicles.size() > 0 )
					    MyUI.get().getEventBus().post(new VehicleGridChangedEvent(vehicles));
					
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});


		VerticalLayout customerGridLayout = new VerticalLayout();
		customerGridLayout.addComponent(customerGrid);
		customerGridLayout.setComponentAlignment(customerGrid, Alignment.TOP_LEFT);
		customerGridLayout.setSpacing(false);
		customerGridLayout.setSizeFull();
		customerGridLayout.setMargin(false);

		MyUI.get().getEventBus().register(new CustomerGridChangeEventHandler(customerGrid));

		return customerGridLayout;
	}
	
	@SuppressWarnings("unchecked")
	private VerticalLayout createVehicleGrid() {


		vehicleGrid = new MyCarVehicleGrid();
		
		
		vehicleGrid.setColumnReorderingAllowed(true);
		vehicleGrid.setHeaderVisible(true);
		vehicleGrid.setResponsive(true);
		vehicleGrid.setRowHeight(25);

		vehicleGrid.addItemClickListener(new ItemClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1772629545957434653L;

			@Override
			public void itemClick(ItemClick event) {

				Vehicle selectedVehicle = (Vehicle) event.getItem();

			}

		});


		VerticalLayout vehicleGridLayout = new VerticalLayout();
		vehicleGridLayout.addComponent(vehicleGrid);
		vehicleGridLayout.setComponentAlignment(vehicleGrid, Alignment.TOP_LEFT);
		vehicleGridLayout.setSpacing(false);
		vehicleGridLayout.setSizeFull();
		vehicleGridLayout.setMargin(false);

		MyUI.get().getEventBus().register(new VehicleGridChangeEventHandler(vehicleGrid));

		return vehicleGridLayout;
	}

	private Component createLookupBottom( ) {
		HorizontalSplitPanel layout = new HorizontalSplitPanel();
		layout.setSplitPosition(50);
		layout.setSizeFull();
		
		layout.setFirstComponent(createCustomerGrid(null));
		layout.setSecondComponent(createVehicleGrid());
		
		return layout;
	}


}
