package com.zoli.beans;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Customer1 {

    @JsonProperty
    String familyName;

    @JsonProperty
    String givenName;


    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }


}
