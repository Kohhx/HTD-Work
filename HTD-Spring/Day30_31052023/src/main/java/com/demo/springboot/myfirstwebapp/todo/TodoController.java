package com.demo.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
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

    @RequestMapping(value ="add-todo", method = RequestMethod.GET)
    public String showTodoPage(ModelMap model) {
        String username = (String) model.get("name");
        Todo todo = new Todo (0,username,"...", LocalDate.now(), false);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value ="add-todo", method = RequestMethod.POST)
    public String addNewTodo(@Valid Todo todo, BindingResult result , ModelMap model) {
        if (result.hasErrors()){
            return "todo";
        }
        String username = (String) model.get("name");
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping(value ="delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteById(id);
        return "redirect:list-todos";
    }


    @RequestMapping(value ="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute(todo);
        return "todo";
    }


    @RequestMapping(value ="update-todo", method = RequestMethod.POST)
    public String updateTodo( ModelMap model,@Valid Todo todo, BindingResult result ) {
        if (result.hasErrors()){
            return "todo";
        }
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }





//    @RequestMapping(value ="add-todo", method = RequestMethod.POST)
////    public String addNewTodo(@RequestParam String description, @RequestParam LocalDate date, ModelMap model) {
//    public String addNewTodo(Todo todo, ModelMap model) {
//        String username = (String) model.get("name");
////        todoService.addTodo(username, description, date, false);
//        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
//        return "redirect:list-todos";
//    }
}
