package com.kat.myapp.event;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.eventbus.Subscribe;
import com.kat.myapp.MyUI;
import com.kat.myapp.backend.database.Customer;
import com.kat.myapp.backend.database.Vehicle;
import com.kat.myapp.backend.exception.ServiceException;
import com.kat.myapp.grid.MyCarCustomerGrid;

public class CustomerGridChangeEventHandler {
	
	final static Logger logger = Logger.getLogger(CustomerGridChangeEventHandler.class);
	
	MyCarCustomerGrid customerGrid;
	
	
	
	public CustomerGridChangeEventHandler(MyCarCustomerGrid customerGrid) {
		super();
		this.customerGrid = customerGrid;
	}



	@Subscribe
	public void handle(CustomerGridChangedEvent event) {
		logger.debug("Handler: " + event.getList());
		Customer firstCustomer = null;
		
		if ( event.getList().size() > 0  ) {
			firstCustomer = event.getList().get(0);
			
			try {
				logger.debug("Extract first customer's vehicle(s) in list"); 
				List<Vehicle> vehicles = Vehicle.getVehiclesByOwner(firstCustomer.getId());
				logger.debug(vehicles);
//				if ( vehicles.size() > 0 )
				    MyUI.get().getEventBus().post(new VehicleGridChangedEvent(vehicles));
				
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		customerGrid.setItems(event.getList());
		if ( event.getList().size() > 0 )
			customerGrid.select(firstCustomer);
	}

}
