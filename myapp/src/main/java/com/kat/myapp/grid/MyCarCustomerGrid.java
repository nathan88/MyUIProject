package com.kat.myapp.grid;


import com.kat.myapp.backend.database.Customer;
import com.vaadin.ui.Grid;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class MyCarCustomerGrid extends Grid<Customer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7214315301825012609L;

	public MyCarCustomerGrid() {
		setSizeFull();


		addColumn(Customer::getCustomerName).setCaption("NAME").setMinimumWidth(50);
		addColumn(Customer::getPrimaryNumber).setCaption("CONTACT").setMinimumWidth(40);
		addColumn(Customer::getAddress).setCaption("ADDRESS").setMinimumWidth(100).setWidth(300);
		addColumn(Customer::getCity).setCaption("CITY").setMinimumWidth(30);
		addColumn(Customer::getState).setCaption("STATE").setMinimumWidth(10);
		addColumn(Customer::getPostalCode).setCaption("POSTAL").setMinimumWidth(10);

		addStyleName("smallgrid");

	}

	public Customer getSelectedRow() {
		return asSingleSelect().getValue();
	}

	public void refresh(Customer Customer) {
		getDataCommunicator().refresh(Customer);
	}

}
