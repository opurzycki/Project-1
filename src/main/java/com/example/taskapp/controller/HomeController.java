package com.example.taskapp.controller;

import com.example.taskapp.mainService.MainService;
import com.example.taskapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String login(){
        return("login");
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }


    @GetMapping("/tables")
    public String tables(){
        return "table";
    }

        MainService mainService;

        @Autowired
        public HomeController(MainService mainService) {
            this.mainService = mainService;
        }

        // obsługa żądania przekierowania na stronę formularza
        @GetMapping("/register")
        public String register(Model model){
            User user = new User();
            // dodajemy atrybut dla obiektu klasy Model ("nazwa stosowanana w html", nazwa bjektu Java)
            model.addAttribute("user", user);
            return "register";  // przekierowanie na widok formularza rejestracji (html)
        }

        // obsługa żądania przekazania obiektu z formularz metodą post
        @PostMapping("/register")
        public String register(@ModelAttribute @Valid User user, BindingResult bindingResult){
            if(bindingResult.hasErrors()){
                return "register";
            }
            // zapis do DB przez serwis użytkownika
            mainService.registerUser(user);
            return "register";
        }
    }


