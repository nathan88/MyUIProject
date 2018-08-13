package com.kat.myapp.form;

import java.util.List;

import org.apache.log4j.Logger;
import org.omg.CORBA.portable.ValueBase;

import com.google.common.eventbus.EventBus;
import com.kat.myapp.MyUI;
import com.kat.myapp.backend.database.Customer;
import com.kat.myapp.backend.database.Lookup;
import com.kat.myapp.backend.exception.ServiceException;
import com.kat.myapp.event.CustomerGridChangedEvent;
import com.kat.myapp.form.validator.EmptyFieldValidator;
import com.vaadin.data.Binder;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class MyCarCustomerLookupForm  extends VerticalLayout {

	final static Logger logger = Logger.getLogger(MyCarCustomerLookupForm.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -3823641830991942212L;
	
	private Lookup<Customer> lookup ;
	
	private TextField byName;
	private TextField byContactNumber;
	private TextField byPlate;

	private Label messageLine;

	public MyCarCustomerLookupForm() {
		super();
		init();
	}

	private void init() {
		Binder<Lookup<Customer>> binder = new Binder<>();
		Customer customer = new Customer();

		// The object that will be edited
		if ( lookup == null ) {
			lookup = new Lookup<>(customer);;
		}
		
		GridLayout layout = new GridLayout(4, 4);
		
		Button go = new Button("Go");
		Button reset = new Button("Reset");
		
		// Row 1
		Label label = new Label("By name");
		layout.addComponent(label, 0, 0);
		byName = new TextField();
		layout.addComponent(byName, 1, 0);

		// Row 2
		label = new Label("By contact number");
		layout.addComponent(label, 0, 1);
		byContactNumber = new TextField();
		layout.addComponent(byContactNumber, 1, 1);
		
		layout.addComponent(reset, 3, 1);
		
		// Row 3
		label = new Label("By license plate");
		layout.addComponent(label, 0, 2);
		byPlate = new TextField();
		layout.addComponent(byPlate, 1, 2);
		
		layout.addComponent(go, 3, 2);
		
		
		// ---------------- Row 4
		messageLine = new Label("");
		layout.addComponent(messageLine, 0, 3, 3, 3);
		
		SerializablePredicate<String> predicate = value -> !byName.getValue().trim().isEmpty() 
				|| !byContactNumber.getValue().trim().isEmpty() || !byPlate.getValue().trim().isEmpty();

		// binding
		binder.forField(byName)
				.withValidator(predicate,
							"At least one field must be provided")
		        .bind(Lookup::getByName, Lookup::setByName); 

		// binding
		binder.forField(byContactNumber)
				.withValidator(predicate,
							"At least one field must be provided")
				.bind(Lookup::getByContactNumber, Lookup::setByContactNumber);

		
		// binding
		binder.forField(byPlate)
				.withValidator(predicate,
							"At least one field must be provided")
		        .bind(Lookup::getByPlate, Lookup::setByPlate);



		// Click listeners for the buttons
		go.addClickListener(event -> {
		    if (binder.writeBeanIfValid(lookup)) {
		    	List<Customer> list = null;
		    	try {
					list = lookup.getResult();
					CustomerGridChangedEvent changeEvent = new CustomerGridChangedEvent(list);
					
					EventBus eventBus = new EventBus();
					logger.debug("Record found: " + list.size());
					MyUI.get().getEventBus().post(changeEvent);
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	if ( list == null )
		            messageLine.setValue("Lookup failed. No match record found.");
		    } else {
//		        BinderValidationStatus<Customer> validate = binder.validate();
		        messageLine.setValue("Correct the error(s) and press save again");
		    }
		});
		reset.addClickListener(event -> {
		    // clear fields by setting null
		    binder.readBean(null);
		});
		
		layout.setSpacing(true);
		logger.debug("set focus on byName");
		byName.focus();
		addComponent(layout);
	}
	
	

}
