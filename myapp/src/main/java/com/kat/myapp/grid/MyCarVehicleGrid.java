package com.kat.myapp.grid;


import com.kat.myapp.backend.database.Vehicle;
import com.vaadin.ui.Grid;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class MyCarVehicleGrid extends Grid<Vehicle> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1816776061584751270L;



	public MyCarVehicleGrid() {
		setSizeFull();


		addColumn(Vehicle::getLicensePlate).setCaption("PLATE")
				.setMinimumWidth(50);
		addColumn(Vehicle::getVin).setCaption("VIN").setMinimumWidth(50);
		addColumn(Vehicle::getVehicleInfo).setCaption("VEHICLE DETAIL")
				.setMinimumWidth(100);
		addColumn(Vehicle::getColor).setCaption("COLOR")
				.setMinimumWidth(30);
		addColumn(Vehicle::getMileage).setCaption("MILEAGE").setMinimumWidth(50);
		
		addStyleName("smallgrid");

	}

	public Vehicle getSelectedRow() {
		return asSingleSelect().getValue();
	}

	public void refresh(Vehicle vehicle) {
		getDataCommunicator().refresh(vehicle);
	}

}
