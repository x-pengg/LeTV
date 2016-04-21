package com.mylive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chan on 16/4/21.
 */

@Controller
public class PlayerController {

    @RequestMapping("/")
    public String hello(Model model){
        model.addAttribute("activityId","A201604210000219");
        return "index";
    }

}
