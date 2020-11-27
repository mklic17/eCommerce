package com.ecommerce.eccomApp.controller;

import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    // inject via application.properties
    private String message = "Hello World";

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

//    @GetMapping("/")
//    public String main(Model model) {
//        model.addAttribute("message", message);
//        model.addAttribute("tasks", tasks);
//
//        return "welcome"; //view
//    }


}
