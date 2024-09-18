package com.ivan.springboot.mvc.controller;

import com.ivan.springboot.mvc.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }

    @GetMapping("/")
    public String showForm(Model theModel) {
        theModel.addAttribute("customer", new Customer());

        return "customer-form";
    }

    @PostMapping("/process-form")
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {

        System.out.println("last name: |" + theCustomer.getLastName() + "|");

        System.out.println("\nBinding result: " + theBindingResult.toString());

        if(theBindingResult.hasErrors()) {
            return "customer-form";
        }
        else return "customer-confirmation";
    }

}
