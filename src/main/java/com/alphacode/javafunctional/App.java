package com.alphacode.javafunctional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.alphacode.javafunctional.App.Gender.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Person> people=List.of(
            new Person("john",MALE),
            new Person("nishan",MALE),
            new Person("saniya",FEMALE),
            new Person("alex",MALE),
            new Person("riya",FEMALE)
        );
        
        System.out.println("-----Imperative approach------");
//        Imperative approach
        List<Person> females=new ArrayList<>();
        
        for(Person person:people) {
        	if(FEMALE.equals(person.gender)) {
        		females.add(person);
        	}
        }
        
        for(Person female:females) {
        	System.out.println(female);
        }
        
        System.out.println("------Declarative approach-------");
//        Declarative approach
        
        Predicate<Person> personPredicate = person -> FEMALE.equals(person.gender);
        people.stream()
        	.filter(personPredicate)
        	.collect(Collectors.toList())
        	.forEach(System.out::println);
        
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
    	MALE,FEMALE
    }
}
