package com.qa.jdbc;

import java.util.ArrayList;
import java.util.List;

import com.qa.jdbc.daos.PersonDAO;
import com.qa.jdbc.domain.Person;

public class Runner {

	public static void main(String[] args) {
		
		// Connection test for when it breaks
//		TestConnection tc = new TestConnection();
//		tc.testConnection();
		
		// CRUD functionality (CREATE, READ ,UPDATE ,DELETE)
		// Using DAO - Data Access Object
		PersonDAO pDAO = new PersonDAO();
		
//		Person p = new Person("Tom", "Smith", 30);
//		Person pe = new Person("James", "Dave", 50);
//		
//		System.out.println(p);
		
//		pDAO.create(p);
//		pDAO.createPrepared(pe);
		
		// Read by ID
		System.out.println(pDAO.readById(1));
		System.out.println(pDAO.readByIdPrepared(1));
		
		System.out.println();
		
		// Read all
		System.out.println(pDAO.readAll());
		System.out.println();
		// Better read all
		List<Person> people = new ArrayList<>();
		people = pDAO.readAll();
		for(Person person : people) {
			System.out.println(person);
		}
	}
}
