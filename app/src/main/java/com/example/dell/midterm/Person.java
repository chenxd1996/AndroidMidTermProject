package com.example.dell.midterm;

/**
 * Created by roger on 2017/11/26.
 */

public class Person {
    public String first_char;
    public String name;
    public String camp;
    public String sex;
    public String native_place_modern;
    public String native_place_ancient;
    public int age;
    public String picture;
    public String description;

    public Person() {

    }

    public Person(String first_char, String name, String camp, String sex, String native_place_ancient,
                  String native_place_modern, int age, String picture, String description) {
        this.first_char = first_char;
        this.name = name;
        this.camp = camp;
        this.sex = sex;
        this.native_place_modern = native_place_modern;
        this.native_place_ancient = native_place_ancient;
        this.age = age;
        this.description = description;
        this.picture = picture;
    }
}
