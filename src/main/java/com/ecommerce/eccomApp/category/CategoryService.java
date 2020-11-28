package com.ecommerce.eccomApp.category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // getAllCategories
    public List<Category> getAllCategory() {
        ArrayList<Category> categoryList = new ArrayList<Category>();
        for(Category cat : categoryRepository.findAll())
            categoryList.add(cat);
        return categoryList;
    }

    // Returns a single category if it Exist or returns NULL
    public Category getCategory(Long Id) {
        return categoryRepository.findById(Id).orElse(null);
    }

    // Adds a category to the Database
    public void addCategory(Category cat) {
        categoryRepository.save(cat);
    }

    // Updates a category with the information if it exists or creates a new category
    public void updateCategory(Long Id, Category cat) {
        categoryRepository.save(cat);
    }


    // Removes a category from the Database
    public void deleteCategory(Long Id) {
        categoryRepository.deleteById(Id);
    }



}
