package com.ecommerce.eccomApp;

import com.ecommerce.eccomApp.category.Category;
import com.ecommerce.eccomApp.catalog.Catalog;
import com.ecommerce.eccomApp.category.CategoryService;
import com.ecommerce.eccomApp.product.Product;
import com.ecommerce.eccomApp.product.ProductService;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Component
public class Application implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    public void afterPropertiesSet() {
        Catalog storefront = Catalog.getCatalog(productService.getAllProducts(), categoryService.getAllCategory());
        Collection<Category> catCol = storefront.getAllStoreCategories();
        Collection<Product> prodCol = storefront.getAllProducts();

//		for(Category cat : catCol) {
//			System.out.println("CategoryName: " + cat.getName());
//		}
//
//		for(Product prod : prodCol) {
//			System.out.println("ProductName: " + prod.getName());
//		}
    }


}
