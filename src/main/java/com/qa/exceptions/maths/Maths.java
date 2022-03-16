package com.qa.exceptions.maths;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Maths {

	public void divide(int a, int b) {
		int result;

		try {

			if (a < b) {
				throw new IntsDivideBadly();
			}

			result = a / b;
			System.out.println(result);

		} catch (IntsDivideBadly idb) {
			idb.printStackTrace();
		} catch (ArithmeticException ae) {
			ae.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Scanner scanner = new Scanner(System.in);

	public void divide() {

		try {

			int a = scanner.nextInt();
			int b = scanner.nextInt();

			if (a > b) {
				throw new IntsDivideBadly();
			}

			System.out.println(a / b);

		} catch (InputMismatchException ime) {
			System.out.println("ERROR: Input must be a number");
		} catch (IntsDivideBadly idb) {
			idb.printStackTrace();
		} catch (ArithmeticException ae) {
			System.out.println(ae.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

}
