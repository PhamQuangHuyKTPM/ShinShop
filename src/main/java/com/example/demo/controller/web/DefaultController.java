package com.example.demo.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class DefaultController {

    @GetMapping("")
    public String defautlURL(){
        return "redirect:/home";
    }

    @GetMapping("/remove-session")
    public String removeSession(){
        return "redirect:/home";
    }

}
