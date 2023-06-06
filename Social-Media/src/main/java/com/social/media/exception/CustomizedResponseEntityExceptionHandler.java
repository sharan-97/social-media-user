package com.social.media.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**ResponseEntityExceptionHandler handles the various http related exceptions thrown automatically, in order to take control of that and add our
  specific error messages and error format we can create as class as shown below and extend. Also you can override some of the methods with our implementation*/
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> allException(Exception ex, WebRequest webRequest){
		System.out.println("hello im in error handling class");
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage(), webRequest.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetails> userNotFoundException(Exception ex, WebRequest webRequest){

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage(), webRequest.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		/**you can also use the below function to fetch the error details instead of just ex.getMessage()**/
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getFieldError().getDefaultMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(errorDetails,HttpStatus.NOT_ACCEPTABLE);

	}

}
