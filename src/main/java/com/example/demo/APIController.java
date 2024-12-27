package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello K8s...!!!";
	}
	
	@GetMapping("/error")
	public ResponseEntity<String> exceptionMethod() {
	    String errorMessage;
	    try {
	        System.out.println("Generating exception");
	        throw new RuntimeException("Custom generated Exception for fluent bit");
	    } catch (RuntimeException e) {
	        errorMessage = "Exception occurred: " + e.getMessage();
	        System.out.println(errorMessage);
	        e.printStackTrace();
	        // Return an HTTP response with the error message and a 500 status
	        return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
	    }
	}


}
