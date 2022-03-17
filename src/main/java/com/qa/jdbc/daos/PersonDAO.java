package com.qa.jdbc.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.qa.jdbc.domain.Person;

public class PersonDAO implements Dao<Person>{

	public static final Logger LOGGER = LogManager.getLogger();

	// Three things we need to connect
	private String connectionURL = "jdbc:mysql://localhost:3306/persondb";
	private String username = "root";
	private String password = "root";

	// Constructor
	public PersonDAO() {}	
	
	// Model from resultSet method ---- From mtSQL to Object --- everyDAO has one of
	// these
	public Person modelFromResultSet(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String firstName = resultSet.getString("first_name");
		String lastName = resultSet.getString("last_name");
		int age = resultSet.getInt("age");

		return new Person(id, firstName, lastName, age);
	}

	// CREATE -- USE
	public int create(Person person) {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				PreparedStatement statement = conn
						.prepareStatement("INSERT INTO people(first_name, last_name, age) VALUES (?, ?, ?);")) {
			
			statement.setString(1, person.getFirstName());
			statement.setString(2, person.getLastName());
			statement.setInt(3, person.getAge());
			
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return 0;
	}

	// READ BY ID - PREPARED
	public Person readByID(int id) {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				PreparedStatement statement = conn.prepareStatement("SELECT * FROM people WHERE id = ?;")) {
			
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			resultSet.next();
			return modelFromResultSet(resultSet);
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		System.out.println("Your person might not exsist!!!");
		return null;
	}

	// READ ALL
	public List<Person> readAll() {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				PreparedStatement statement = conn.prepareStatement("SELECT * FROM people;")) {

			ResultSet resultSet = statement.executeQuery();

			List<Person> people = new ArrayList<>();

			while (resultSet.next()) {
				people.add(modelFromResultSet(resultSet));
				
			}
			
			return people;
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

	// UPDATE
	public int update(Person person) {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				PreparedStatement statement = conn
						.prepareStatement("UPDATE people SET first_name = ?, last_name = ?, age = ? WHERE id = ?;")) {

			statement.setString(1, person.getFirstName());
			statement.setString(2, person.getLastName());
			statement.setInt(3, person.getAge());
			statement.setInt(4, person.getId());

			return statement.executeUpdate();

		} catch (SQLException e) {
			e.getStackTrace();
		}
		return 0;
	}

	// DELETE
	public int delete(int id) {
		try (Connection conn = DriverManager.getConnection(connectionURL, username, password);
				PreparedStatement statement = conn.prepareStatement("DELETE FROM people WHERE id = ?;")) {
			
			statement.setInt(1, id);
			
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return 0;
	}
}
