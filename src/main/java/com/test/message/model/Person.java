package com.test.message.model;

public class Person {
    private String name;
    private Integer city_id;

    public Person(String name, Integer city_id) {
        this.name = name;
        this.city_id = city_id;
    }

    public String name() {
        return name;
    }

    public Integer city_id() {
        return city_id;
    }
}
