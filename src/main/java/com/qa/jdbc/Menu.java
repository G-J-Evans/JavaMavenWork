package com.qa.jdbc;

import java.util.Scanner;

import com.qa.jdbc.controller.CrudController;

public class Menu {
	private Scanner scanner = new Scanner(System.in);

	public Menu() {}

	public void personControllerRunMenu(CrudController<?> crudController) {
		menuTitleMessage(crudController);

		boolean flag = true;
		while (flag) {
			menuCRUDChoice(crudController);

			flag = menuEndOrGoAgain();
		}
		scanner.close();
	}

	private void menuTitleMessage(CrudController<?> crudController) {
		System.out.println("--- " + crudController.controllerTitleString() + " ---");
	}
	
	private boolean menuCRUDChoice(CrudController<?> crudController) {
		System.out.println();
		System.out.println("What opperation would you like to do? CREATE/READ/UPDATE/DELETE");
		
		boolean flag = true;
		while (flag) {
			
		String opperation = scanner.nextLine().toLowerCase();
	
			if (opperation.equals("create")) {
				crudController.create();
				return true;

			} else if (opperation.equals("read")) {
				menuReadIDOrAll(crudController);
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

	private void menuReadIDOrAll(CrudController<?> crudController) {
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
