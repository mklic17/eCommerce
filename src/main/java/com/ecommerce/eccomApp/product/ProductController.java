package com.ecommerce.eccomApp.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping({"product"})    // SET TO BE THE MAIN
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{Id}") // Returns a single product
    public ModelAndView getProduct(@PathVariable Long Id) {
        ModelAndView mnv = new ModelAndView();
        mnv.addObject("product", productService.getProduct(Id));
        mnv.setViewName("product/show");
        return mnv;
    }

    @GetMapping({"", "/", "/home"}) // Return all Products
    public ModelAndView getAllProducts() {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("product/list");
        mnv.addObject("products", productService.getAllProducts());
        return mnv;
    }

}
