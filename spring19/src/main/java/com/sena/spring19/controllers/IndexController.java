package com.sena.spring19.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
        @GetMapping(path = {"/","","/index","/home"})
        public String index(Model m){
            return "index";
    }
}
