package com.groupm.com.groupm.model;

import java.io.Serializable;

/**
 * Created by Jerick.Duguran on 4/19/2016.
 */
public class Country implements Serializable{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
