package com.ecommerce.eccomApp.product;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="Product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(name="name")
    private String name;


    @Column(name="price")
    private double price;


    @Column(name="summary")
    private String summary;


    @Column(name="description")
    private String description;


    @Column(name="image")
    private String image;


    public boolean equals(Object thatObject) {
        if (thatObject == this) {
            return true;
        }
        if (!(thatObject instanceof Product)) { // Check the type and validate that it's
            return false;
        }
        Product curr = (Product)thatObject;// Cast to the proper Object
        if (curr.getId() == (this.Id)
                && curr.getName() == this.name
                && curr.getPrice() == (this.price)
                && curr.getSummary().equals(this.summary)
                && curr.getDescription().equals(this.description)
        ) {
            return true;
        }
        return false;
    }

    // OVERRIDE HashCode
    @Transient
    private int theHashCode = 0;
    public int hashCode() {
        if(theHashCode == 0) {
            theHashCode = 17;
            theHashCode = theHashCode * 37 + this.Id.hashCode();
            theHashCode = theHashCode * 37 + this.name.hashCode();
            theHashCode = theHashCode * 37 + this.summary.hashCode();
            theHashCode = theHashCode * 37 + this.description.hashCode();
            theHashCode = theHashCode * 37 + this.image.hashCode();
        }
        return theHashCode;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

}
