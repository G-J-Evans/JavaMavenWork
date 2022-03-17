package com.qa.jdbc.controller;

import java.util.List;

public interface CrudController<T> {
	
	void controllerTitleMessage();
	
	int create();

	List<T> readAll();
	
	T readByID();
	
	T update();
	
	int delete();
	
	}
