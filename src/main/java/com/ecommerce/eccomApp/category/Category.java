package com.ecommerce.eccomApp.category;


import com.ecommerce.eccomApp.product.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long Id;

    @Column(name="name")
    private String name;

    @Column(name="parent")
    private Long parent;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Products_In_Category",
            joinColumns = {
                    @JoinColumn( name = "category_Id", referencedColumnName = "Id")
            },
            inverseJoinColumns = {
                    @JoinColumn( name = "product_Id", referencedColumnName = "Id")
            }
    )
    private Set<Product> products = new HashSet<Product>();


    public Category(String name, long parent) {
        this.name = name;
        this.parent = parent;
    }

    public Category() {
    }

    public void printProductsInCategory() {
        for(Product p : products) {
            System.out.println("---- Product: " + p.getName());
        }
    }

    public void addToproducts(Product prod) {
        products.add(prod);
    }

    public void removeProductAssignment(Product prod) {
        if(products.contains(prod)) {
            products.remove(prod);
        }
    }


    // https://codepumpkin.com/hashset-internal-implementation/
// Suggests to override equals and hashCode method when implementing with custom obj
    public boolean equals(Object thatObject) {
        if (thatObject == this) {
            return true;
        }
        if (!(thatObject instanceof Category)) { // Check the type and validate that it's
            return false;
        }
        Category curr = (Category)thatObject;// Cast to the proper Object
        if (curr.getId().equals(this.Id)
                && curr.getName() == this.name
                &&  curr.getParent().equals(this.parent)
                && curr.getProducts().equals(this.products)
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
            theHashCode = theHashCode * 37 + this.parent.hashCode();
            theHashCode = theHashCode * 37 + this.products.hashCode();
        }
        return theHashCode;
    }


    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public Long getParent() {
        return Id;
    }

    public Set<Product> getProducts(){
        return products;
    }


}
