package com.mir.collections;

/**
 * Created by Mir on 23/09/2016.
 */
public class Customer {

    private int id;
    private String name;

    public Customer(int i, String n){
        this.id=i;
        this.name=n;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}