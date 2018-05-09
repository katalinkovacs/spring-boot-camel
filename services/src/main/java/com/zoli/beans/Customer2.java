package com.zoli.beans;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Customer2 {


    @JsonProperty
    String firstName;

    @JsonProperty
    String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
