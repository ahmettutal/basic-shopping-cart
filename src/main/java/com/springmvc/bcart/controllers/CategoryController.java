package com.springmvc.bcart.controllers;

import com.springmvc.bcart.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main() {
        return new ModelAndView("home", "serverTime", "asdasdasd");
    }

}
