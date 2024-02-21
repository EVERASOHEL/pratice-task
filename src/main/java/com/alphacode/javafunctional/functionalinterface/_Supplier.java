package com.alphacode.javafunctional.functionalinterface;

import java.util.List;
import java.util.function.Supplier;

public class _Supplier {

	public static void main(String[] args) {
		
		System.out.println(getDBConnectionUrl());
		
//		using supplier interface
		System.out.println(getDBConnectionUrlSupplier.get());
		System.out.println("Users : "+getUsers.get());
		
	}
	
	static String  getDBConnectionUrl() {
		return "jdbc://localhost:3600/specindia";
	}

	static  Supplier<String> getDBConnectionUrlSupplier = () 
			-> "jdbc://localhost:3600/specindia";
			
	static Supplier<List<String>> getUsers = ()
			-> List.of(
						"Prince",
						"Nizam",
						"Parshav"
					);
			
}
