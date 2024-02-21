package com.alphacode.javafunctional.functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {

	public static void main(String[] args) {
		
//		this is normal java function
		Customer Mariacustomer = new Customer("Maria","9999");
		greetCustomer(Mariacustomer);
		greetCustomerV2(Mariacustomer,false);
		
//		Consumer Functional Interface
		getCustomerConsumer.accept(Mariacustomer);
		
		getCustomerConsumerV2.accept(Mariacustomer, false);
	}
	
	static BiConsumer<Customer,Boolean> getCustomerConsumerV2= (customer,showPhoneNumber) ->
	System.out.println("Hello "+customer.customerName+
			" thanks for reistrering phone number "+
			(showPhoneNumber ? customer.customerPhoneNumber : "************"));
	
	static Consumer<Customer> getCustomerConsumer= customer ->
	System.out.println("Hello "+customer.customerName+
			" thanks for reistrering phone number "+
			customer.customerPhoneNumber);
	
//	normal java function 
	static void greetCustomer(Customer customer) {
		System.out.println("Hello "+customer.customerName+
				" thanks for reistrering phone number "+
				customer.customerPhoneNumber);
	}
	
	static void greetCustomerV2(Customer customer,Boolean showPhoneNumber) {
		System.out.println("Hello "+customer.customerName+
				" thanks for reistrering phone number "+
				(showPhoneNumber ? customer.customerPhoneNumber : "************"));
	}
	
	static class Customer{
		
		private final String customerName;
		private final String customerPhoneNumber;
		
		public Customer(String customerName,String customerPhoneNumber) {
			this.customerName=customerName;
			this.customerPhoneNumber=customerPhoneNumber;
		}
		
	}
	
}
