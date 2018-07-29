package com.kat.myapp.event;

import org.apache.log4j.Logger;

import com.google.common.eventbus.Subscribe;
import com.kat.myapp.grid.MyCarCustomerGrid;
import com.kat.myapp.grid.MyCarVehicleGrid;

public class VehicleGridChangeEventHandler {
	
	final static Logger logger = Logger.getLogger(VehicleGridChangeEventHandler.class);
	
	MyCarVehicleGrid vehicleGrid;
	
	
	
	public VehicleGridChangeEventHandler(MyCarVehicleGrid vehicleGrid) {
		super();
		this.vehicleGrid = vehicleGrid;
	}



	@Subscribe
	public void handle(VehicleGridChangedEvent event) {
		logger.debug("Handler: " + event.getList());
		vehicleGrid.setItems(event.getList());
	}

}
