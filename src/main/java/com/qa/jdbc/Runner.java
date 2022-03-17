package com.qa.jdbc;

public class Runner {

	public static void main(String[] args) {
		
		// Connection test for when it breaks
//		TestConnection tc = new TestConnection();
//		tc.testConnection();
		
		new Menu().personControllerRunMenu();
	}
}
