package org.ivan.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;

@Controller
public class HelloWorldController {

    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    @RequestMapping("/proccessForm")
    public String processForm() {
        return "helloworld";
    }

    @RequestMapping("/processFormVersionTwo")
    public String processFormVersionTwo(HttpServletRequest request, Model model) {

        String theName = request.getParameter("studentName");

        theName = theName.toUpperCase();

        String message = "Yoo! " + theName;

        model.addAttribute("message", message);

        return "helloworld";
    }

    @GetMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName, Model model) {
        theName = theName.toUpperCase();

        String message = "Yooo! " + theName;

        model.addAttribute("message", message);

        return "helloworld";
    }
}
