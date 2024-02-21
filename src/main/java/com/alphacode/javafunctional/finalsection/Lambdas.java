package com.alphacode.javafunctional.finalsection;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Lambdas {

	public static void main(String[] args) {
		
//		quickly return value then use
		Function<String, String> upperCaseName = name -> name.toUpperCase();
		
//		some login for
		Function<String, String> upperCaseName1 = name -> {
			
//			logic
			if(name.isBlank()) throw new IllegalArgumentException("Please Enter Name");
			return name.toUpperCase();
			
		};
		
//		pass two parameter
		BiFunction<String,Integer,String> upperCaseName2 = (name,age)->{
			
//			logic
			if(name.isBlank()) throw new IllegalArgumentException("Please Enter Name");
			System.out.println(age);
			return name.toUpperCase();
		};
		
		System.out.println(upperCaseName2.apply("John", 23));
	}
}
