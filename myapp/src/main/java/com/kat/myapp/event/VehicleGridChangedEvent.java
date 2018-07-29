package com.kat.myapp.event;

import java.util.List;

import com.kat.myapp.backend.database.Vehicle;

public class VehicleGridChangedEvent {

	private List<Vehicle> list;
	
	
	public VehicleGridChangedEvent(List<Vehicle> list) {
		super();
		this.list = list;
	}
	
	public List<Vehicle> getList() {
		return list;
	}

	public void setList(List<Vehicle> list) {
		this.list = list;
	}
	
}
