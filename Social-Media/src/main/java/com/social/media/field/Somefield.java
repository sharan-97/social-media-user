package com.social.media.field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**below annotation will ignore the below field mentioned, from the JSON structure when it is sent as response to client**/
@JsonIgnoreProperties("field3")
public class Somefield {
	
	/**similarly, below annotation will also ignore the below field in the JSON structure when it is sent as response to client**/
	@JsonIgnore
	private String field1;
	private String field2;
	private String field3;
	
	
	public Somefield(String field1, String field2, String field3) {
		super();
		
	
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	
	public String getField1() {
		return field1;
	}
	public String getField2() {
		return field2;
	}
	public String getField3() {
		return field3;
	}
	
	

}
