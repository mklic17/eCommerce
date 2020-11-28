package com.ecommerce.eccomApp.catalog;


import com.ecommerce.eccomApp.category.Category;
import com.ecommerce.eccomApp.product.Product;

import java.util.*;



// No Lombok, No Database, created at the start of the program and when maunally forced to rebuild
public class Catalog {

    private static Catalog storefrontCatalog = null; // singleton Constructor

    private Map<Long, List<Long>> catalogMap = null;      // Category ID (parent) --> List<Category Id's>  (children)
    private Map<Long, Category> categoryMap = null;            // Category ID --> Category
    private Map<Long, Product> productMap = null;              // Product ID --> Product
    //	private Map<String, Long> prodNameToId = null;
    private Map<String, Long> catNameToId = null;
    // Product(

    //////////////////////////////// STATIC  START ////////////////////////////////////////
    public static Catalog getCatalog(List<Product> prodList, List<Category> catList) {
        if (storefrontCatalog == null) {
            storefrontCatalog = new Catalog(prodList, catList);
        }
        return storefrontCatalog;
    }

    public static Catalog getCatalog() {
        return storefrontCatalog;
    }


    public static Catalog rebuildCatalog(List<Product> prodList, List<Category> catList) {
        storefrontCatalog = null;
        storefrontCatalog = new Catalog(prodList, catList);
        return storefrontCatalog;
    }


    //////////////////////////////// STATIC STOP ////////////////////////////////////////


    // private constructor
    private Catalog(List<Product> prodList, List<Category> catList) {

        catalogMap = new HashMap<Long, List<Long>>();
        categoryMap = new HashMap<Long, Category>();
        productMap = new HashMap<Long, Product>();
        catNameToId = new HashMap<String, Long>();

        // initalize productMap
        for(Product prod : prodList) {
            productMap.put(prod.getId(), prod);
            System.out.println("Product: " + prod.getName());
//			prodNameToId.put(prod.getName(), prod.getId());
        }

        // initialize categoryMap
        for(Category categ : catList) {

            long categoryID = categ.getId();
            categoryMap.put(categoryID, categ);
            catNameToId.put(categ.getName(), categ.getId());

//			// initalize CatalogMap
            if (catalogMap.containsKey(categoryID)) { // if the category is in the catalog append to the list
                List<Long> liCat = catalogMap.get(categoryID);
                liCat.add(categoryID);
                catalogMap.get(categoryID).add(categoryID);
            } else {
//				ArrayList<long> catIds = new ArrayList<long>();
//				catalogMap.put(categoryID, new ArrayList<long>());
            }
        }


    }

    public Collection<Category> getAllStoreCategories() {
        return categoryMap.values();
    }

    public Collection<Product> getAllProducts() {
        return productMap.values();
    }


    public List<Product> getAllProductsInCategory(String categoryId) {
        // check to see if there is products in the Category
        List <Product> productList = new ArrayList<Product>();
        Category cat = categoryMap.get(categoryId);

//		for(Category prodId : cat.getCategoryAssignments()) {
//			productList.add(productMap.get(prodId));
//		}
        return productList;
    }

    public Category getCategoryByName(String name) {
        return categoryMap.get(catNameToId.get(name));
    }

    public Product getProductById(Long prodId) {
        return productMap.get(prodId);
    }


//


//	private void buildTheCatalogNavigation(Category currRoot) {
//		ArrayList<Category> catList = getParentCategories(currRoot.getId());
//		CategoryFactory.setChildCategoryList(currRoot, catList);
//	    	if (catList.size() == 0) {
//	    		return;
//	     }
//	     for (Category cat : catList) {
//	    	 buildTheCatalogNavigation(cat);
//	     }
//	}


//	private ArrayList<Category> getParentCategories(Long parentId) {
//		ArrayList<Category> theArrayList = new ArrayList<Category>();
//		for(Category cat : categoryMap.values()) {
//			if(cat.getParentCategoryId() == parentId) {
//				theArrayList.add(cat);
//			}
//		}
//		return theArrayList;
//	}


//	 private static void printHierarchyTree(Category localRoot, int level) {
//		 for (int i = 0; i < level; i++) {
//			 System.out.print("\t");
//		 }
//		 System.out.println(localRoot.getName());
//
//		 List<Category> subordinates = localRoot.getChildCategoryList();
//		 System.out.print(" ");
//		 for (Category e : subordinates) {
//			 printHierarchyTree(e, level + 1);
//		 }
//	 }

}

