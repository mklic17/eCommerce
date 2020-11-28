package com.ecommerce.eccomApp.product;


import com.ecommerce.eccomApp.catalog.Catalog;
import com.ecommerce.eccomApp.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;


@Controller
@RequestMapping({"product"})    // SET TO BE THE MAIN
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping({"", "/", "/home"}) // Return all Products
    public ModelAndView getAllProducts() {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("product/list");
        mnv.addObject("products", productService.getAllProducts());
        return mnv;
    }


    @GetMapping({"/new"})
    public ModelAndView addProduct() {  // Adds a product to the DB with new values
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("product/newProductform");
        mnv.addObject("product", new Product());
        mnv.addObject("heading", "New Product");
        mnv.addObject("allCategories", getCategories());
        mnv.addObject("button", "create");
        return mnv;
    }


    @PostMapping("/create") // POST mapping for new product
//    public ModelAndView createProduct(@Valid Product prod, BindingResult result) {
    public ModelAndView createProduct(Product prod, BindingResult result) {

            ModelAndView mnv = new ModelAndView();
        if(result.hasErrors()) {
            mnv.setViewName("product/newProductform");
            mnv.addObject("heading", "New Product");
            mnv.addObject("product", prod);
//	        mnv.addObject("categories", getCategories());
            return mnv;
        }
        productService.addProduct(prod);
        mnv.addObject("product", productService.getProduct(prod.getId()));
        mnv.setViewName("product/show");
        return mnv;
    }


    @GetMapping("/edit/{Id}")  // GET  method for editing a product
    public ModelAndView editProduct(@PathVariable Long Id) {
        ModelAndView mnv = new ModelAndView();
        Product prod = productService.getProduct(Id);
        mnv.setViewName("product/newProductform");
        mnv.addObject("heading", "Edit Product");
        mnv.addObject("product", prod);
        mnv.addObject("button", "update");
        return mnv;
    }

    @GetMapping("/delete/{Id}")
    public ModelAndView deleteProduct(@PathVariable Long Id) {
        ModelAndView mnv = new ModelAndView();
        Product prod = productService.getProduct(Id);
        if (prod != null) {
            productService.deleteProduct(Id);
        }
        mnv.setViewName("product/list");
        mnv.addObject("products", productService.getAllProducts());
        return mnv;
    }


    private Collection<Category> getCategories() {
        return Catalog.getCatalog().getAllStoreCategories();
    }


}
