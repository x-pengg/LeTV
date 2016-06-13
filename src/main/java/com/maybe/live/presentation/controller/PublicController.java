package com.maybe.live.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Tate
 * @date: 2016/5/17 14:26
 */
@Controller
public class PublicController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("pageNotFound")
    public String pageNotFound() {
        return "404";
    }

    @RequestMapping("about")
    public String about() {
        return "about";
    }

}
