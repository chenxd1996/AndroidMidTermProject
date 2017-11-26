package com.example.dell.midterm;

/**
 * Created by lenovo on 2017/11/26.
 */
public class Contactlist {
    private String name;
    private String price;
    private String id;

    public String getName() {
        return name;
    }
    public String getprice() {
        return price;
    }
    public String getId() {
        return id;
    }
    public Contactlist(String i, String n, String p) {
        id = i;
        name = n;
        price = p;
    }
}