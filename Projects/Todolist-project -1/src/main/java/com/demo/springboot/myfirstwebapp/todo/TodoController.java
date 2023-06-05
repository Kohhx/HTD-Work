package com.demo.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@SessionAttributes("username")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = todoService.getAllTodos("kohhx");
        System.out.println(todos);
        model.addAttribute("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value ="add-todo", method = RequestMethod.GET)
    public String showTodoPage(ModelMap model) {
        String username = (String) model.get("username");
        Todo todo = new Todo (0,username,"", null, false);
        model.put("type", "create");
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value ="add-todo", method = RequestMethod.POST)
    public String addNewTodo(@Valid Todo todo, BindingResult result , ModelMap model) {
        if (result.hasErrors()){
            model.put("type", "create");
            System.out.println(result);
            return "todo";
        }
        String username = (String) model.get("username");
        todoService.addTodo(username, todo);
        return "redirect:list-todos";
    }

    @RequestMapping(value ="delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodoById(id);
        return "redirect:list-todos";
    }


    @RequestMapping(value ="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.getTodoById(id);
        var date = todo.getTargetDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(dateTimeFormatter);
        model.put("formattedDate",formattedDate);
        model.addAttribute(todo);
        model.put("type", "update");
        return "todo";
    }


    @RequestMapping(value ="update-todo", method = RequestMethod.POST)
    public String updateTodo( ModelMap model,@Valid Todo todo, BindingResult result ) {
        if (result.hasErrors()){
            model.put("type", "update");
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
