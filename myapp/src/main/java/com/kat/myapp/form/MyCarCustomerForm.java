package com.kat.myapp.form;

import com.kat.myapp.backend.database.Customer;
import com.kat.myapp.backend.exception.ServiceException;
import com.kat.myapp.form.validator.EmailFieldValidator;
import com.kat.myapp.form.validator.EmptyFieldValidator;
import com.kat.myapp.form.validator.PhoneFieldValidator;
import com.vaadin.data.Binder;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MyCarCustomerForm  extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3823641830991942212L;
	
	private Customer customer;
	
	private TextField firstName;
	private TextField lastName;
	private TextField primaryNumber;
	private TextField secondaryNumber;
	private TextField email;
	private TextField address;
	private TextField city;
	private TextField state;
	private TextField country;
	private Label messageLine;

	public MyCarCustomerForm() {
		super();
		init();
	}

	private void init() {
		Binder<Customer> binder = new Binder<>();

		// The object that will be edited
		if ( customer == null )
			customer = new Customer();
		
		GridLayout layout = new GridLayout(6, 7);
		
		// ---------------- Row 1
		Label label = new Label("First name");
		layout.addComponent(label, 0, 0);
		firstName = new TextField();
		firstName.setValueChangeMode(ValueChangeMode.EAGER);
		layout.addComponent(firstName, 1, 0);

		
		label = new Label("Last name");
		layout.addComponent(label, 3, 0, 4, 0);
		lastName = new TextField();
		lastName.setValueChangeMode(ValueChangeMode.EAGER);
		layout.addComponent(lastName, 5, 0);
		
		// ---------------- Row 2
		label = new Label("Primary phone");
		layout.addComponent(label, 0, 1);
		primaryNumber = new TextField();
		primaryNumber.setValueChangeMode(ValueChangeMode.EAGER);
		layout.addComponent(primaryNumber, 1, 1);

		
		label = new Label("Secondary phone");
		layout.addComponent(label, 3, 1, 4, 1);
		secondaryNumber = new TextField();
		secondaryNumber.setValueChangeMode(ValueChangeMode.EAGER);
		layout.addComponent(secondaryNumber, 5, 1);
		
		// ---------------- Row 3
		label = new Label("Email");
		layout.addComponent(label, 0, 2);
		email = new TextField();
		email.setValueChangeMode(ValueChangeMode.EAGER);
		layout.addComponent(email, 1, 2);
		
		// ---------------- Row 4
		label = new Label("Address");
		layout.addComponent(label, 0, 3);
		address = new TextField();
		address.setWidth("100%");
		address.setValueChangeMode(ValueChangeMode.EAGER);
		layout.addComponent(address, 1, 3, 5, 3);

		// ---------------- Row 5
		label = new Label("City");
		layout.addComponent(label, 0, 4);
		city = new TextField();
		city.setValueChangeMode(ValueChangeMode.EAGER);
		layout.addComponent(city, 1, 4);
		
		label = new Label("State");
		layout.addComponent(label, 2, 4);
		state = new TextField();
		state.setWidth(30, Unit.PIXELS);
		state.setValueChangeMode(ValueChangeMode.EAGER);
		layout.addComponent(state, 3, 4);
		
		label = new Label("Country");
		layout.addComponent(label, 4, 4);
		country = new TextField();
		country.setValueChangeMode(ValueChangeMode.EAGER);
		layout.addComponent(country, 5, 4);
		
		// ---------------- Row 6
		HorizontalLayout actions = new HorizontalLayout();
		
		Button save = new Button("Save");
		Button reset = new Button("Reset");
		actions.setWidth("100%");
		actions.addComponents(save, reset);
		
		layout.addComponent(actions, 5, 5);
		
		// ---------------- Row 7
		messageLine = new Label("");
		layout.addComponent(messageLine, 0, 6, 5, 6);

		// first name validators
		binder.forField(firstName)
		        .withValidator(new EmptyFieldValidator("Required field can not be empty"))
		        .bind(Customer::getFirstName, Customer::setFirstName);

		// last name validators
		binder.forField(lastName)
		        .withValidator(new EmptyFieldValidator("Required field can not be empty"))
		        .bind(Customer::getLastName, Customer::setLastName);

		
		// Primary phone validators
		binder.forField(primaryNumber)
	        	.withValidator(new EmptyFieldValidator("Required field can not be empty"))
		        .withValidator(new PhoneFieldValidator("Incorrect phone number format", false))
		        .bind(Customer::getPrimaryNumber, Customer::setPrimaryNumber);

		// Secondary phone validators
		binder.forField(secondaryNumber)
		        .withValidator(new PhoneFieldValidator("Incorrect phone number format", true))
		        .bind(Customer::getSecondaryNumber, Customer::setSecondaryNumber);
		
		// Email validators
		binder.forField(email)
		        .withValidator(new EmailFieldValidator("Incorrect email address", true))
		        .bind(Customer::getEmail, Customer::setEmail);


		// First name and last name are required fields
		firstName.setRequiredIndicatorVisible(true);
		lastName.setRequiredIndicatorVisible(true);
		primaryNumber.setRequiredIndicatorVisible(true);

		// Click listeners for the buttons
		save.addClickListener(event -> {
		    if (binder.writeBeanIfValid(customer)) {
		    	try {
					customer.addCustomer();
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        messageLine.setValue("Info for " + customer.getFirstName() + " " + customer.getLastName() + " is saved");
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
		addComponent(layout);
	}
	
	

}
