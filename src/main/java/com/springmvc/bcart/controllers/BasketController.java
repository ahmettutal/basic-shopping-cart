package com.springmvc.bcart.controllers;

import com.springmvc.bcart.model.Basket;
import com.springmvc.bcart.model.Product;
import com.springmvc.bcart.services.Strategy;
import org.springframework.beans.factory.BeanFactory;
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

    @Autowired(required = true)
    BeanFactory beanFactory;


    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ModelAndView getBaskets() {

        Strategy basketStrategy = beanFactory.getBean("basket", Strategy.class);
        Strategy productStrategy = beanFactory.getBean("product", Strategy.class);
        Map<String, Double> basketMap = new HashMap<String, Double>();
        List<Object> products = new ArrayList<Object>();
        List<Object> baskets = basketStrategy.findAll();

        for (Object object : baskets) {
            Basket basket = (Basket) object;
            products.add(productStrategy.findById(basket.getProductId()));
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

        Strategy basketStrategy = beanFactory.getBean("basket", Strategy.class);
        basketStrategy.deleteByID(productId); // Delete Old Records for This ProductID
        basketStrategy.save(new Basket(productId, count)); // Then insert a new Basket Item

        return "OK";
    }

}
