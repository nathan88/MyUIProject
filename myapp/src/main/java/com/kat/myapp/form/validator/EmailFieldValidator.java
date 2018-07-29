package com.kat.myapp.form.validator;


import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;

public class EmailFieldValidator extends AbstractValidator<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2482954077256062843L;
	
	private String errorMessage;
	private boolean emptyOk;

	public EmailFieldValidator(String errorMessage, boolean emptyOk) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.emptyOk = emptyOk;
		
	}

	@Override
	public ValidationResult apply(String value, ValueContext context) {
		String email = value;
		
		if ( email.trim().length() == 0  &&  emptyOk)
			return ValidationResult.ok();
		
		EmailValidator validator = new EmailValidator(errorMessage);
		return validator.apply(value, context);
   	     
  				
	
	}

	

}
