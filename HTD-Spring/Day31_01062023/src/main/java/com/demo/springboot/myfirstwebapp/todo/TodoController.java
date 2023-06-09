package com.demo.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUsername("kohhx");
        System.out.println(todos);
        model.addAttribute("todos", todos);
        return "listTodos";
    }
}
