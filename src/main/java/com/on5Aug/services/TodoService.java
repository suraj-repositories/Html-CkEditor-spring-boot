package com.on5Aug.services;

import java.util.List;

import com.on5Aug.entity.Todo;

public interface TodoService {
	
	Todo saveTodo(Todo todo);
	
	void deleteTodo(Todo todo);
	
	Todo findById(Long id);
	
	List<Todo> getAllTodos();
	
}
