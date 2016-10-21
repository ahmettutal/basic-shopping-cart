package com.springmvc.bcart.controllers;

import com.springmvc.bcart.model.Category;
import com.springmvc.bcart.model.Product;
import com.springmvc.bcart.services.CategoryService;
import com.springmvc.bcart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView main() {

        List<Category> categories = categoryService.getCategories();
        List<Product> products = productService.getProducts();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("products", products);

        return modelAndView;
    }

    @RequestMapping(value = "/create-category", method = RequestMethod.GET)
    public ModelAndView getProductsWithCategoryId() {

        List<Category> categories = categoryService.getCategories();

        return new ModelAndView("home", "categories", categories);
    }

    @RequestMapping(value = "/create-category", method = RequestMethod.POST)
    public void createCategory(@RequestParam("count") int count) {
        categoryService.save("Category" + count, "Category" + count);
    }

    @RequestMapping(value = "/create-product", method = RequestMethod.POST)
    public void createProduct(@RequestParam("count") Double count) {
        productService.save("Product" + count.intValue(), "Product" + count.intValue(), count * 13.62, "Category1");
    }

}
