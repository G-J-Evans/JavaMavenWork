package com.qa.jdbc.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.qa.jdbc.domain.Person;

public class PersonDAO {

	public static final Logger LOGGER = LogManager.getLogger();

	// Three things we need to connect
	private String connectionURL = "jdbc:mysql://localhost:3306/persondb";
	private String username = "root";
	private String password = "root";
	
	// Model from resultSet method ---- From mtSQL to Object
	public Person personFromResultSet(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("id");
		String firstName = resultSet.getString("first_name");
		String lastName = resultSet.getString("last_name");
		int age = resultSet.getInt("age");
		return new Person (id, firstName, lastName, age);
	}

	// CREATE -- DONT USE
	public void create(Person person) {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				Statement statement = conn.createStatement()) {
			statement.executeUpdate("INSERT INTO people(first_name, last_name, age) VALUES ('" + person.getFirstName()
					+ "', '" + person.getLastName() + "', " + person.getAge() + ");");
			System.out.println("Person Created");
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	// CREATE EASY MODE - USE
	public void createPrepared(Person person) {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				PreparedStatement statement = conn
						.prepareStatement("INSERT INTO people(first_name, last_name, age) VALUES (?, ?, ?);")) {
			statement.setString(1, person.getFirstName());
			statement.setString(2, person.getLastName());
			statement.setInt(3, person.getAge());
			statement.executeUpdate();
			System.out.println("Person Created");
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
	

	// READ BY ID
	public Person readById(int id) {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				Statement statement = conn.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM people WHERE id = " + id);
			resultSet.next();
			return personFromResultSet(resultSet);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return null;
		
	}
	
	
	// READ BY ID - PREPARED
	public Person readByIdPrepared(int id) {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				PreparedStatement statement = conn
						.prepareStatement("SELECT * FROM people WHERE id = ?;")) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return personFromResultSet(resultSet);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		System.out.println("Your person might not exsist!!!");
		return null;
	}
	
	// READ ALL
	public List<Person> readAll() {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				PreparedStatement statement = conn
						.prepareStatement("SELECT * FROM people")) {
			ResultSet resultSet = statement.executeQuery();
			List<Person> people = new ArrayList<>();
			while (resultSet.next()) {
				people.add(personFromResultSet(resultSet));		
		}
			System.out.println("People have been listed");
			return people;
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return null;
	}
	

	// UPDATE
	public void updatePrepared(Person person) {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				PreparedStatement statement = conn
						.prepareStatement("UPDATE people SET first_name = ?, last_name = ?, age = ? WHERE id = ?")) {
			statement.setString(1, person.getFirstName());
			statement.setString(2, person.getLastName());
			statement.setInt(3, person.getAge());
			statement.setInt(4, person.getId());
			statement.executeUpdate();
			System.out.println("Person Updated");
		} catch (SQLException e) {
			LOGGER.error(e);
		}

	}

	// DELETE
	public void delete(int personId) {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				PreparedStatement statement = conn
						.prepareStatement("DELETE FROM people WHERE id = ?")) {
			statement.setInt(1, personId);
			statement.executeUpdate();
			System.out.println("Person Deleted");
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
}
