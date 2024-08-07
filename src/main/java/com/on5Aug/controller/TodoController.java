package com.on5Aug.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.on5Aug.entity.Todo;
import com.on5Aug.services.HtmlSanitizer;
import com.on5Aug.services.TodoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TodoController {

	@Autowired
	private TodoService service;
	

	@ModelAttribute
	public void addAttributes(Model model) {
	    if (!model.containsAttribute("todo")) {
	        model.addAttribute("todo", new Todo());
	    }
	}
	
	@GetMapping("/")
	public String getCdnPage() {
		return "redirect:/cdn";
	}
	
	@GetMapping("/cdn")
	public String todosCDNPage(Model model) {
		model.addAttribute("todos", service.getAllTodos());
		model.addAttribute("todo", new Todo());
		return "todos-cdn";
	}
	
	
	@GetMapping("/local")
	public String todosLocalPage(Model model) {
		model.addAttribute("todos", service.getAllTodos());
		model.addAttribute("todo", new Todo());
		return "todos-local";
	}
	


	@PutMapping("/save")
	public String saveTodo(@ModelAttribute Todo todo,Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		todo.setDescription(HtmlSanitizer.sanitize(todo.getDescription())); // sanitizing the input
	    service.saveTodo(todo);

	    redirectAttributes.addFlashAttribute("success", "Todo Created Successfully!");
	    String referer = request.getHeader("Referer");
	    return "redirect:" + referer;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteTodo(@PathVariable Long id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		service.deleteTodo(service.findById(id));
		redirectAttributes.addFlashAttribute("success", "Todo deleted Successfully!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	
}
