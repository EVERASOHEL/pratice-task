package com.alphacode.javafunctional.optionals;

import java.util.Optional;

public class Main {

	public static void main(String[] args) {
//		Object value = Optional.ofNullable(null)
//					.orElseGet(()->"default value");
//		
//		Object exception = Optional.ofNullable(null)
//				.orElseThrow(()-> new IllegalStateException("exception"));
		

//		System.out.println(value);
//		System.out.println(exception);
		
		Optional.ofNullable(null)
			.ifPresent(value1->{
//				logic
				System.out.println(value1);
			});
		
		Optional.ofNullable(null)
		.ifPresentOrElse(email-> System.out.println("Sending email to "+email),
						()->{
							System.out.println("Cannot send email.");
						}
				);
		
	}
	
}
