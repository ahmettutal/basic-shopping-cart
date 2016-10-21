package com.springmvc.bcart.controllers;

import com.springmvc.bcart.model.Product;
import com.springmvc.bcart.services.BasketService;
import com.springmvc.bcart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasketController {

    @Autowired
    ProductService productService;

    @Autowired
    BasketService basketService;

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ModelAndView getBaskets() {
        return new ModelAndView("basket", "baskets", basketService.getBaskets());
    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public void addToBasket(@RequestParam("productId") String productId,
                            @RequestParam("count") Double count) throws Exception {

        Product product = productService.getProduct(productId);

        if (product == null)
            throw new Exception("Product Not Found !");

        basketService.save(product.getProductId(), count);
    }

}
