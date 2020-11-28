package com.ecommerce.eccomApp.category;

public class ProductHolder {

    private Long theProduct;
    private Long categoryId;

    public ProductHolder(Long catId) {
        this.categoryId = catId;
    }

    public Long getTheProduct() {
        return theProduct;
    }


    public Long getCategoryId() {
        return this.categoryId;
    }

}