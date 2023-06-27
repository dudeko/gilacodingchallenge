package com.gilasw.codingchallenge.model;

public class MessageCategory {

    private String name;

    public static MessageCategory create() {
        return new MessageCategory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MessageCategory name(String name) {
        this.setName(name);
        return this;
    }
}
