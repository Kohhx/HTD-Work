package com.project.EmployeeApplication.employee;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("username")
public class EmployeeController {

    private final EmployeeService employeeService;
    private String DEFAULTFRPROILEURL = "https://res.cloudinary.com/duadcuueg/image/upload/v1685708964/bubble-gum-avatar-icon_knuvhr.png";

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "list-employees", method = RequestMethod.GET)
    public String listAllEmployees(ModelMap model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        model.put("defaultProfileUrl", DEFAULTFRPROILEURL);
        return "listEmployees";
    }

    @RequestMapping(value = "add-employee", method = RequestMethod.GET)
    public String newEmployeePage(ModelMap model) {
        Employee employee = new Employee();
        model.put("defaultProfileUrl", DEFAULTFRPROILEURL);
        model.put("employee", employee);
        model.put("type", "create");
        model.put("title", "Create New Employee");
        model.put("gender", List.of("male","female"));
        return "employee";
    }

    @RequestMapping(value = "add-employee", method = RequestMethod.POST)
    public String createNewEmployee(ModelMap model, @Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            model.put("type", "create");
            model.put("defaultProfileUrl", DEFAULTFRPROILEURL);
            model.put("title", "Create New Employee");
            return "employee";
        }
        employeeService.addEmployee(employee);
        return "redirect:list-employees";
    }

    @RequestMapping(value = "delete-employee")
    public String deleteEmployee(@RequestParam("id") int employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return "redirect:list-employees";
    }

    @RequestMapping(value = "update-employee", method = RequestMethod.GET)
    public String updateEmployeePage(@RequestParam("id") int employeeId, ModelMap model) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.put("defaultProfileUrl", DEFAULTFRPROILEURL);
        model.put("employee", employee);
        model.put("gender", List.of("male","female"));
        model.put("type", "update");
        model.put("title", "Update Employee");
        return "employee";
    }

    @RequestMapping(value = "update-employee", method = RequestMethod.POST)
    public String updateEmployeePage(ModelMap model, @Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            model.put("type", "update");
            model.put("title", "Update Employee");
            return "employee";
        }
        employeeService.updateEmployeeById(employee);
        return "redirect:list-employees";
    }
}
