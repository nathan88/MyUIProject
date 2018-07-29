package com.kat.myapp.form.validator;


import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;

public class EmptyFieldValidator extends AbstractValidator<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2482954077256062843L;
	
	private String errorMessage;

	public EmptyFieldValidator(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		
	}

	@Override
	public ValidationResult apply(String value, ValueContext context) {
		String fld = value;
		
		if ( fld.trim().length() > 0)
   	    	return ValidationResult.ok();
   	     
   	     
		//return false if nothing matches the input
		return ValidationResult.error(String.format(errorMessage));
				
	
	}

	

}
