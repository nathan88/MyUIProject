package com.kat.myapp.form.validator;


import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;

public class RequiredOptionalFieldValidator extends AbstractValidator<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2482954077256062843L;
	
	private String errorMessage;
	private String groupValues;

	public RequiredOptionalFieldValidator(String errorMessage, String groupValues) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.groupValues = groupValues;
		
	}

	@Override
	public ValidationResult apply(String value, ValueContext context) {
		String fld = value;
		
		if ( groupValues.trim().length() > 0 )
			return ValidationResult.ok();
		
		if ( fld.trim().length() > 0 )
   	    	return ValidationResult.ok();
   	     
   	     
		//return false if nothing matches the input
		return ValidationResult.error(String.format(errorMessage));
				
	
	}

	

}
