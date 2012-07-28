package com.scholastic.framework.persongenerator;

import com.scholastic.framework.Controller;

/**
 * @author prashant
 * @category The purpose of this class is to provide an API for the auto genertion of the common information that is required to generate a person.
 */
public class PersonGeneratorController extends Controller {

	/**
	 * @return Returns a new instance of the PersonGeneratorController
	 */
	public static PersonGeneratorController getInstance() {
		return new PersonGeneratorController();
	}
	private PersonGeneratorController () {
	}
	
	/**
	 * @return Returns an auto generated FirstName
	 */
	public String getFirstName() {
		PersonGeneratorFuncGetName v_fn;
		v_fn = new PersonGeneratorFuncGetName();
		v_fn.startFunction();
		return v_fn.getFirstName();
	}
	
	/**
	 * @return Returns an auto generated LastName
	 */
	public String getLastName () {
		PersonGeneratorFuncGetName v_fn;
		v_fn = new PersonGeneratorFuncGetName();
		v_fn.startFunction();
		return v_fn.getLastName();
	}
	
	/**
	 * @return Returns an auto-generated Address information
	 */
	public String getAddress () {
		PersonGeneratorFuncGetAddress v_fn;
		v_fn = new PersonGeneratorFuncGetAddress();
		v_fn.startFunction();
		return v_fn.getAddress();
	}
	

}
