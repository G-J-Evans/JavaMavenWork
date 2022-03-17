package com.qa.jdbc.controller;

import java.util.List;

public interface CrudController<T> {
	
	String controllerTitleString();
	
	int create();

	List<T> readAll();
	
	T readByID();
	
	T update();
	
	int delete();
	
	}
