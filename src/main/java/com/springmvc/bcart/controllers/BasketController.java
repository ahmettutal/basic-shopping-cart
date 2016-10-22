package com.springmvc.bcart.controllers;

import com.springmvc.bcart.model.Basket;
import com.springmvc.bcart.model.Product;
import com.springmvc.bcart.services.BasketService;
import com.springmvc.bcart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BasketController {

    @Autowired
    ProductService productService;

    @Autowired
    BasketService basketService;

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ModelAndView getBaskets() {

        Map<String, Double> basketMap = new HashMap<String, Double>();
        List<Product> products = new ArrayList<Product>();
        List<Basket> baskets = basketService.getBaskets();

        for (Basket basket : baskets) {
            products.add(productService.getProduct(basket.getProductId()));
            basketMap.put(basket.getProductId(), basket.getCount());
        }

        ModelAndView modelAndView = new ModelAndView("basket");
        modelAndView.addObject("products", products);
        modelAndView.addObject("basketMap", basketMap);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public String addToBasket(@RequestParam("productId") String productId,
                            @RequestParam("count") Double count) throws Exception {

        Product product = productService.getProduct(productId);

        if (product == null)
            return "Product Not Found !";

        basketService.deleteBasket(productId); // Delete Old Records for This ProductID
        basketService.save(product.getProductId(), count); // Then insert a new Basket Item

        return "OK";
    }

}
