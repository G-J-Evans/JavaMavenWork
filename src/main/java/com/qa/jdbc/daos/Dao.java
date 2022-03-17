package com.qa.jdbc.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	
	T modelFromResultSet(ResultSet resultSet) throws SQLException;
	
	int create(T t);
	
	T readByID(int id);
	
	List<T> readAll();
	
	int update(T t);
	
	int delete(int id);
	
}
