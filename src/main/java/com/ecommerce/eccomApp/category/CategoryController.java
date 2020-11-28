package com.ecommerce.eccomApp.category;

import com.ecommerce.eccomApp.catalog.CatalogController;
import com.ecommerce.eccomApp.product.Product;
import com.ecommerce.eccomApp.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value="category")   // getAllCategory()
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    @GetMapping("/")   // getAllCategory()
    public ModelAndView getAllCategory() {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("category/list");
        mnv.addObject("categories", categoryService.getAllCategory());
        return mnv;
    }


    @GetMapping("/{Id}")
    public ModelAndView getCategory(@PathVariable Long Id) {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("category/show");
        Category curr = categoryService.getCategory(Id);

        String parent = null;
        if (curr.getParent() != null) {
            parent = categoryService.getCategory(curr.getParent()).getName();
        } else {
            parent = "Already a Root";
        }

        mnv.addObject("parentCategory", parent);
        mnv.addObject("category", curr);
        mnv.addObject("products", curr.getProducts());
        mnv.addObject("allProd", productService.getAllProducts());
        ProductHolder temp = new ProductHolder(Id);
        mnv.addObject("ProductHolder", temp);
        return mnv;
    }


    @GetMapping("/new")
    public ModelAndView createCategory() {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("category/newCategoryForm");
        mnv.addObject("category", new Category());
        mnv.addObject("allProducts", productService.getAllProducts());
        return mnv;
    }


    @PostMapping("/new")
//    public ModelAndView postCategory(@Valid Category cat, BindingResult result) {
    public ModelAndView postCategory(Category cat, BindingResult result) {
        System.out.println("--------------MK --------------" + cat.getName() + " ***************************** here");
        ModelAndView mnv = new ModelAndView();
        if(result.hasErrors()) {
            mnv.setViewName("category/newCategoryForm");
            mnv.addObject("category", cat);
            return mnv;
        }
        categoryService.addCategory(cat);
        mnv.setViewName("category/list");
        mnv.addObject("categories", categoryService.getAllCategory());
        return mnv;
    }


    @GetMapping("/edit/{Id}")
    public ModelAndView editCategory(@PathVariable Long Id) {
        ModelAndView mnv = new ModelAndView();
        Category cat = categoryService.getCategory(Id);
        // if exists REVIEW
        mnv.setViewName("category/newCategoryform");
        mnv.addObject("category", cat);
        return mnv;
    }


    @GetMapping("/delete/{Id}")
    public ModelAndView deleteCategory(@PathVariable Long Id) {
        ModelAndView mnv = new ModelAndView();
        Category cat = categoryService.getCategory(Id);
        if(cat != null) {
            categoryService.deleteCategory(Id);
        }
        mnv.setViewName("category/list");
        mnv.addObject("categories", categoryService.getAllCategory());
        return mnv;
    }


    @PostMapping("/addProductAssignment")
//    public String postProductAssignment(@Valid ProductHolder prodHold) {
    public String postProductAssignment(ProductHolder prodHold) {

        ModelAndView mnv = new ModelAndView();
        // if result.hasError REVIEW
        Product p = productService.getProduct(prodHold.getTheProduct());
        Long catId = prodHold.getCategoryId();
        Category curr = categoryService.getCategory(catId);
        curr.addToproducts(p);
        categoryService.updateCategory(catId, curr);
        return "redirect:/category/" + prodHold.getCategoryId();
    }


    @GetMapping("/deleteProduct/{catId}/{prodId}")
    public String deleteProductAssignment(@PathVariable("prodId") Long prodId, @PathVariable("catId") Long catId) {
        System.out.println("Inside the DELETE");
        System.out.println("The productId" + prodId);
        System.out.println("The caategoryId" + catId);

        Category curr = categoryService.getCategory(catId);
        Product prod = productService.getProduct(prodId);
        curr.removeProductAssignment(prod);
        categoryService.updateCategory(curr.getId(), curr);

        return "redirect:/category/" + catId;
    }

    @GetMapping("/rebuild")
    public ModelAndView rebuildIndex() {
        ModelAndView mnv = new ModelAndView();
        List<Product> allProducts = productService.getAllProducts();
        List<Category> allCategory = categoryService.getAllCategory();
        CatalogController.rebuildCatalog(allProducts, allCategory);
        mnv.setViewName("admin/adminDashboard");
        return mnv;
    }


    public String getNameFromID(Long Id) {
        return categoryService.getCategory(Id).getName();
    }


}
