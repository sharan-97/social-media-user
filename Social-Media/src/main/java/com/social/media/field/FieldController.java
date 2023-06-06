package com.social.media.field;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FieldController {

		@GetMapping("/filtering")
	 	public Somefield filtering() {
			
			return new Somefield("value1","value2","value3");
	 		
	 	}
}
