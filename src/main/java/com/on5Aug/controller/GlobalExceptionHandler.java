package com.on5Aug.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.on5Aug.services.TodoService;

@ControllerAdvice
public class GlobalExceptionHandler {


	@Autowired
	private TodoService service;
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationExceptions(MethodArgumentNotValidException ex, Model model) {
        BindingResult bindingResult = ex.getBindingResult();
        model.addAttribute("bindingResult", bindingResult);
        // You need to put the 'todo' attribute in the model because the validation failed
        model.addAttribute("todo", bindingResult.getTarget());
        model.addAttribute("todos", service.getAllTodos()); // Add other necessary attributes for the view
        return "todos-cdn"; // Replace with the actual name of your form view
    }
}
