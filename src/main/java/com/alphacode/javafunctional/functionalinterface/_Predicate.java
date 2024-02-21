package com.alphacode.javafunctional.functionalinterface;

import java.util.function.Predicate;

public class _Predicate {

	public static void main(String[] args) {
		
		System.out.println(isPhoneNumberValid("916358259049"));
		System.out.println(isPhoneNumberValid("9163582590422"));
		System.out.println(isPhoneNumberValid("096358259042"));
		
		System.err.println("----------Predicate Function----------");
		System.out.println(isPhoneNumberValidPredicate.test("916358259049"));
		System.out.println(isPhoneNumberValidPredicate.test("9163582590422"));
		System.out.println(isPhoneNumberValidPredicate.test("096358259042"));

		System.err.println("--------------------------------");
		System.out.println(
				"Is phone number valid and contains number 3 = "+
				isPhoneNumberValidPredicate.and(containsNumber3).test("916358259042")
		);
		
		System.out.println(
				"Is phone number valid and contains number 3 = "+
				isPhoneNumberValidPredicate.or(containsNumber3).test("916458259049")
		);
		
		
		
	}
	
	static boolean isPhoneNumberValid(String phoneNumber) {
		return phoneNumber.startsWith("91") &&  phoneNumber.length()==12;
	}
	
	static Predicate<String> isPhoneNumberValidPredicate = phoneNumber ->
		phoneNumber.startsWith("91") &&  phoneNumber.length()==12;
		
	static Predicate<String> containsNumber3 = phoneNumber ->
		phoneNumber.contains("3");
	
}
