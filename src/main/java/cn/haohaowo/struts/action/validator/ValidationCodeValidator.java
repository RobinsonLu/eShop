package cn.haohaowo.struts.action.validator;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class ValidationCodeValidator extends FieldValidatorSupport {

	private String sessionValidationCode;
	private boolean trim = true;
	
	
	public String getSessionValidationCode() {
		return sessionValidationCode;
	}


	public void setSessionValidationCode(String sessionValidationCode) {
		this.sessionValidationCode = sessionValidationCode;
	}


	public boolean isTrim() {
		return trim;
	}


	public void setTrim(boolean trim) {
		this.trim = trim;
	}


	public void validate(Object object) throws ValidationException {
		// validate method
		Object objectValidateCode = getFieldValue(sessionValidationCode, object);
		String fieldName = getFieldName();
		String fieldValue = (String) getFieldValue(fieldName, object);
		if(fieldValue == null) {
			return ;
		}
		if(trim) {
			fieldValue = fieldValue.trim();
		}
		if(fieldValue.length() == 0) {
			return ;
		}
		if(!fieldValue.equals(objectValidateCode)) {
			addFieldError(fieldName, object);
		}
	}

}
