package com.alphacode.javafunctional.functionalinterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {
	
	public static void main(String[] args) {
		
		
		int increment = increment(1);
		System.out.println(increment);
		
//		Function takes 1 argument and produces 1 result
		System.err.println("--------Function-----------");
		Integer increment2 = incrementByOneFunction.apply(1);
		System.out.println("increment value : "+increment2);
		
		int multiplyBy10Function = multipleBy10.apply(increment2);
		System.out.println("multiply : "+multiplyBy10Function);
		
		Function<Integer, Integer> addBy1AndThenMultiplyBy10 = 
				incrementByOneFunction.andThen(multipleBy10);
		
		System.out.println(addBy1AndThenMultiplyBy10.apply(3));
		
		System.err.println("--------------");
		
//		BiFunction takes 2 argument and produce 1 result
		System.out.println(incrementByOneandMultiply(4,100));
		System.out.println(incrementByOneAndMultiplyBiFunction.apply(4,100));
		
	}

	static Function<Integer,Integer> incrementByOneFunction = 
			number -> number+1;

	static Function<Integer,Integer> multipleBy10 = 
			number -> number*10;
			
	static int increment(int number) {
		return number+1;
	}
	
	static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyBiFunction =
			(numberToIncrementByOne,numberToMultiplyBy)
				-> (numberToIncrementByOne +1) * numberToMultiplyBy;
	
	static int incrementByOneandMultiply(int number,int numToMultiplyBy) {
		return (number+1)*numToMultiplyBy;
	}
	
}
