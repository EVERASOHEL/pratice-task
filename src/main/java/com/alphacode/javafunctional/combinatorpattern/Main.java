package com.alphacode.javafunctional.combinatorpattern;

import java.time.LocalDate;

import com.alphacode.javafunctional.combinatorpattern.CustomerRegistrationValidator.ValidationResult;

public class Main {

	public static void main(String[] args) {
		Customer customer= new Customer(
					"Alice",
					"alice@gmail.com",
					"+916332",
					LocalDate.of(2000, 4, 1)
				);
		
//		System.out.println(new CustomerValidatorService().isValid(customer));
		
//		Using combinator pattern
		ValidationResult result = CustomerRegistrationValidator
			.isEmailValid()
			.and(CustomerRegistrationValidator.isPhoneNumberValid())
			.and(CustomerRegistrationValidator.isAnAdult())
			.apply(customer);
		
		System.out.println(result);
		
		if(result!=ValidationResult.SUCCESS) {
			throw new IllegalStateException(result.name());
		}
	}
	
}
