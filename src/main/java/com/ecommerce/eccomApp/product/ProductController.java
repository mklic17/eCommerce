package com.ecommerce.eccomApp.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{Id}") // Returns a single product
    public ModelAndView getProduct(@PathVariable Long Id) {
        ModelAndView mnv = new ModelAndView();
        mnv.addObject("product", productService.getProduct(Id));
        mnv.setViewName("product");
        return mnv;
    }

}
