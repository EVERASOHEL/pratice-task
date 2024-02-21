package com.alphacode.javafunctional.streams;


import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class _Stream {
	public static void main(String[] args) {
		
		List<Person> people=List.of(
	            new Person("john",Gender.MALE),
	            new Person("nishan",Gender.MALE),
	            new Person("saniya",Gender.FEMALE),
	            new Person("alex",Gender.MALE),
	            new Person("Priya",Gender.FEMALE)
//	            new Person("iriya",Gender.PREFER_NOT_TO_SAY)
	    );
		
//		people.stream()
//				.map(person -> person.name)
//				.mapToInt(String::length)
//				.forEach(System.out::println);
	
//		Predicate<Person> personPredicate = person -> Gender.FEMALE.equals(person.gender);
		Predicate<Person> personPredicate = person -> Gender.PREFER_NOT_TO_SAY.equals(person.gender);
		
//		boolean containsOnlyFemales = people.stream()
//			.allMatch(personPredicate);
		
//		boolean containsOnlyFemales = people.stream()
//				.anyMatch(personPredicate);
		
		boolean containsOnlyFemales = people.stream()
				.noneMatch(personPredicate);
		
		System.out.println(containsOnlyFemales);
		
	}
	
	 static class Person{
	    	private final String name;
	    	private final Gender gender;

	        Person(String name, Gender gender) {
	            this.name = name;
	            this.gender = gender;
	        }

	        @Override
	        public String toString() {
	            return "Person{" +
	                    "name='" + name + '\'' +
	                    ", gender=" + gender +
	                    '}';
	        }
	    }
	    
	    enum Gender{
	    	MALE,FEMALE,PREFER_NOT_TO_SAY
	    }
}
