package com.ecommerce.eccomApp.product;

import lombok.Data;

@Data
public class ProductForm {
    // matches the Product fields

    private Long Id;
    private String name;
    private double price;
    private String summary;
    private String description;
    private String image;

}
