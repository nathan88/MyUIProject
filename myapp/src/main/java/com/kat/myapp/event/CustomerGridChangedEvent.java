package com.kat.myapp.event;

import java.util.List;

import com.kat.myapp.backend.database.Customer;

public class CustomerGridChangedEvent {

	private List<Customer> list;
	
	
	public CustomerGridChangedEvent(List<Customer> list) {
		super();
		this.list = list;
	}
	
	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}
	
}
