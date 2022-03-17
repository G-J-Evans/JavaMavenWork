package com.qa.jdbc;

import java.util.Scanner;

import com.qa.jdbc.controller.CrudController;
import com.qa.jdbc.controller.PersonController;
import com.qa.jdbc.daos.Dao;
import com.qa.jdbc.daos.PersonDAO;

public class Menu {
	private Scanner scanner = new Scanner(System.in);
	private CrudController crudController = new CrudController(new PersonDAO());

	public Menu() {}

	public void personControllerRunMenu() {
		crudController.controllerTitleMessage();

		boolean flag = true;
		while (flag) {
			menuPersonCRUDChoice();

			flag = menuEndOrGoAgain();
		}
		scanner.close();
	}

	
	
	private boolean menuPersonCRUDChoice() {
		boolean flag = false;

		System.out.println();
		System.out.println("What opperation would you like to do? CREATE/READ/UPDATE/DELETE");

		String opperation = scanner.nextLine().toLowerCase();

		 while (flag) {
			if (opperation.equals("create")) {
				crudController.create();
				return true;

			} else if (opperation.equals("read")) {
				menuReadIDOrAll();
				return true;

			} else if (opperation.equals("update")) {
				crudController.update();
				return true;

			} else if (opperation.equals("delete")) {
				crudController.delete();
				return true;

			} else if (opperation.equals("exit")) {
				return true;

			} else {
				System.out.println("Your input was invalid try again (EXIT to EXIT)");

			}
		} 
		return false;
	}

	private void menuReadIDOrAll() {
		boolean flag = false;

		do {
			System.out.println();
			System.out.println("Would you like to do? READ ALL/READ BY ID/EXIT");

			String choice = scanner.nextLine().toLowerCase();

			if (choice.equals("read all")) {
				crudController.readAll();

			} else if (choice.equals("read by id") || choice.equals("readbyid")) {
				crudController.readByID();

			} else if (choice.equals("exit")) {

			} else {
				System.out.println("Your input wasn't recoginised try again");
				flag = true;
			}
		} while (flag);

	}

	private boolean menuEndOrGoAgain() {
		boolean flag = true;

		while (flag) {
			System.out.println();
			System.out.println("Would you like to do another opperation? (y/n)");

			String choice = scanner.nextLine();

			if (choice.equalsIgnoreCase("y")) {
				System.out.println();
				System.out.println("--- Next Opperation ---");
				return true;

			} else if (choice.equalsIgnoreCase("n")) {
				System.out.println("Application stopping...");
				return false;

			} else {
				System.out.println("You entered invalid input try again");

			}
		}
		return flag;
	}
}
