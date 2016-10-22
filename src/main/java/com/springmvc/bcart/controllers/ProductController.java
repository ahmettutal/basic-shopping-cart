package com.springmvc.bcart.controllers;

import com.mongodb.*;
import com.mongodb.util.JSON;
import com.springmvc.bcart.model.Category;
import com.springmvc.bcart.model.Product;
import com.springmvc.bcart.services.CategoryService;
import com.springmvc.bcart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.UnknownHostException;
import java.util.ArrayList;
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
        modelAndView.addObject("currentCategoryId", "");

        return modelAndView;
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public ModelAndView getProductsWithCategoryId(@PathVariable("categoryId") String categoryId) {

        List<Category> categories = categoryService.getCategories();
        List<Product> products = productService.findProductsWithCategoryId(categoryId);

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("products", products);
        modelAndView.addObject("currentCategoryId", categoryId);

        return modelAndView;
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public ModelAndView getProduct(@PathVariable("productId") String productId) {

        Product product = productService.getProduct(productId);

        ModelAndView modelAndView = new ModelAndView("productdetail");
        modelAndView.addObject("product", product);

        return modelAndView;
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public void init() {
        try {
            System.out.println("Mongodb Initialize Start...");

            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("basicshoppingcart");
            DBCollection collection = db.getCollection("Category");

            List<DBObject> objects = new ArrayList<DBObject>();
            objects.add((DBObject) JSON.parse("{'categoryId' : 'books', 'name' : 'Books'}"));
            objects.add((DBObject) JSON.parse("{'categoryId' : 'flowers', 'name' : 'Flowers'}"));
            objects.add((DBObject) JSON.parse("{'categoryId' : 'jackets', 'name' : 'Jackets'}"));
            objects.add((DBObject) JSON.parse("{'categoryId' : 'sport', 'name' : 'Sport'}"));
            objects.add((DBObject) JSON.parse("{'categoryId' : 'outdoor', 'name' : 'Outdoor'}"));

            collection.insert(objects); // insert Categories for first setup

            collection = db.getCollection("Product");
            objects = new ArrayList<DBObject>();
            objects.add((DBObject) JSON.parse("{'productId' : 'book1', 'name' : 'Book1', 'price' : 42.17, 'categoryId' : 'books'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'book2', 'name' : 'Book2', 'price' : 6.21, 'categoryId' : 'books'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'book3', 'name' : 'Book3', 'price' : 324.12, 'categoryId' : 'books'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'book4', 'name' : 'Book4', 'price' : 234.4, 'categoryId' : 'books'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'book5', 'name' : 'Book5', 'price' : 2.17, 'categoryId' : 'books'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'book6', 'name' : 'Book6', 'price' : 431.54, 'categoryId' : 'books'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'book7', 'name' : 'Book7', 'price' : 21.4, 'categoryId' : 'books'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'book8', 'name' : 'Book8', 'price' : 66, 'categoryId' : 'books'}"));

            objects.add((DBObject) JSON.parse("{'productId' : 'flowers1', 'name' : 'Flowers1', 'price' : 42.17, 'categoryId' : 'flowers'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'flowers2', 'name' : 'Flowers2', 'price' : 1.17, 'categoryId' : 'flowers'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'flowers3', 'name' : 'Flowers3', 'price' : 43.17, 'categoryId' : 'flowers'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'flowers4', 'name' : 'Flowers4', 'price' : 5.17, 'categoryId' : 'flowers'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'flowers5', 'name' : 'Flowers5', 'price' : 88.17, 'categoryId' : 'flowers'}"));

            objects.add((DBObject) JSON.parse("{'productId' : 'jackets1', 'name' : 'Jackets1', 'price' : 5.17, 'categoryId' : 'jackets'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'jackets2', 'name' : 'Jackets2', 'price' : 23.17, 'categoryId' : 'jackets'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'jackets3', 'name' : 'Jackets3', 'price' : 76.17, 'categoryId' : 'jackets'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'jackets4', 'name' : 'Jackets4', 'price' : 43.17, 'categoryId' : 'jackets'}"));

            objects.add((DBObject) JSON.parse("{'productId' : 'sport1', 'name' : 'Sport1', 'price' : 22.5, 'categoryId' : 'sport'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'sport2', 'name' : 'Sport2', 'price' : 67.5, 'categoryId' : 'sport'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'sport3', 'name' : 'Sport3', 'price' : 72.5, 'categoryId' : 'sport'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'sport4', 'name' : 'Sport4', 'price' : 43.5, 'categoryId' : 'sport'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'sport5', 'name' : 'Sport5', 'price' : 3.5, 'categoryId' : 'sport'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'sport6', 'name' : 'Sport6', 'price' : 6.5, 'categoryId' : 'sport'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'sport7', 'name' : 'Sport7', 'price' : 9.5, 'categoryId' : 'sport'}"));

            objects.add((DBObject) JSON.parse("{'productId' : 'outdoor1', 'name' : 'Outdoor1', 'price' : 92.5, 'categoryId' : 'outdoor'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'outdoor2', 'name' : 'Outdoor2', 'price' : 4.5, 'categoryId' : 'outdoor'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'outdoor3', 'name' : 'Outdoor3', 'price' : 2.5, 'categoryId' : 'outdoor'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'outdoor4', 'name' : 'Outdoor4', 'price' : 5.5, 'categoryId' : 'outdoor'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'outdoor5', 'name' : 'Outdoor5', 'price' : 7.5, 'categoryId' : 'outdoor'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'outdoor6', 'name' : 'Outdoor6', 'price' : 43.5, 'categoryId' : 'outdoor'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'outdoor7', 'name' : 'Outdoor7', 'price' : 67.5, 'categoryId' : 'outdoor'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'outdoor8', 'name' : 'Outdoor8', 'price' : 568.5, 'categoryId' : 'outdoor'}"));
            objects.add((DBObject) JSON.parse("{'productId' : 'outdoor9', 'name' : 'Outdoor9', 'price' : 6.25, 'categoryId' : 'outdoor'}"));

            collection.insert(objects); // insert Products for first setup

            System.out.println("Mongodb Initialize End...");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

}
