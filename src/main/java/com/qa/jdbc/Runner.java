package com.qa.jdbc;

import com.qa.jdbc.controller.PersonController;
import com.qa.jdbc.daos.PersonDAO;

public class Runner {

	public static void main(String[] args) {
		
		// Connection test for when it breaks
//		TestConnection tc = new TestConnection();
//		tc.testConnection();
		
		new Menu().personControllerRunMenu(new PersonController(new PersonDAO()));
	}
}
