package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final HandlerMapping resourceHandlerMapping;
    private final RestTemplateAutoConfiguration restTemplateAutoConfiguration;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService, @Qualifier("resourceHandlerMapping") HandlerMapping resourceHandlerMapping, RestTemplateAutoConfiguration restTemplateAutoConfiguration) {
        this.employeeService = theEmployeeService;
        this.resourceHandlerMapping = resourceHandlerMapping;
        this.restTemplateAutoConfiguration = restTemplateAutoConfiguration;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        // get employees form db
        List<Employee> employees = employeeService.findAll();

        // add them to spring model
        model.addAttribute("employees", employees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Employee employee = new Employee();
        theModel.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee theEmployee) {
        // save the employee to db
        employeeService.save(theEmployee);

        // use redirect to prevent multiple submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String update(@RequestParam("employeeId") int theId, Model theModel) {
        // get the employee from the service
        Employee employee = employeeService.findById(theId);

        // set employee in the model
        theModel.addAttribute("employee", employee);

        // send over our form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        // delete the employee
        employeeService.deleteById(theId);

        // redirect to /employees/list
        return "redirect:/employees/list";
    }
}
