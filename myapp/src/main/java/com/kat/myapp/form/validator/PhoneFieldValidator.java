package com.kat.myapp.form.validator;

import com.googlecode.gentyref.GenericTypeReflector;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;

public class PhoneFieldValidator extends AbstractValidator<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2482954077256062843L;
	
	private String errorMessage;
	private boolean emptyOk;

	public PhoneFieldValidator(String errorMessage, boolean emptyOk) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.emptyOk = emptyOk;
		
	}

	@Override
	public ValidationResult apply(String value, ValueContext context) {
		String phoneNo = value;
		
		if ( phoneNo.trim().length() == 0  &&  emptyOk)
			return ValidationResult.ok();
		
		if (phoneNo.matches("\\d{10}"))  //validate phone numbers of format "1234567890"
			return ValidationResult.ok();
		
		 if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))   	//validating phone number with -, . or spaces
			return ValidationResult.ok(); 
		 
		 if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))  //validating phone number with extension length from 3 to 5
		 	return ValidationResult.ok();
		 
   	     if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) 				//validating phone number where area code is in braces ()
   	    	return ValidationResult.ok();
   	     
   	     
		//return false if nothing matches the input
		return ValidationResult.error(String.format(errorMessage));
				
	
	}

	

}
