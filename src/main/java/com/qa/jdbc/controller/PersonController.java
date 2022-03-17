package com.qa.jdbc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.qa.jdbc.daos.PersonDAO;
import com.qa.jdbc.domain.Person;

public class PersonController implements CrudController<Person> {

	private Scanner scanner = new Scanner(System.in);
	private PersonDAO personDAO;
	
	public PersonController(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	public void controllerTitleMessage() {
		System.out.println("--- People menu ---");
	}
	
	public int create() {
		System.out.println("Please enter a first name");
		String firstName = scanner.nextLine();
		
		System.out.println("Please enter a second name");
		String lastName = scanner.nextLine();
		
		System.out.println("Please enter an age");
		int age = scanner.nextInt();

		int count = personDAO.create(new Person(firstName, lastName, age));
		System.out.println("Number of people created: " + count);
		return count;
	}
	
	public List<Person> readAll() {
		List<Person> people = new ArrayList<>();
		
		System.out.println("List of everyone");
		
		people = personDAO.readAll();
		
		for(Person person : people) {
			System.out.println(person);
		}
		return people;
	}
	
	public Person readByID() {
		System.out.println("Please enter an ID to read");
		
		Person person = personDAO.readByID(scanner.nextInt());
		
		System.out.println(person);
		
		return person;
	}
	
	public Person update() {
		System.out.println("Please enter the ID of the person to update");
		int id = scanner.nextInt();
		
		System.out.println("Please enter the updated first name");
		scanner.nextLine();
		String firstName = scanner.nextLine();
		
		System.out.println("Please enter the updated second name");
		String lastName = scanner.nextLine();
		
		System.out.println("Please enter the updated age");
		int age = scanner.nextInt();
		
		int count = personDAO.update(new Person(id, firstName, lastName, age));
		System.out.println("Number of people updated: " + count);
		System.out.println("Your updated person is: " + personDAO.readByID(id));
		
		return personDAO.readByID(id);
	}
	
	public int delete() {
		System.out.println("Please enter an ID to delete");
		int count = personDAO.delete(scanner.nextInt());
		
		System.out.println("Number of people deleted: " + count);
		return count;
	}
	
}
