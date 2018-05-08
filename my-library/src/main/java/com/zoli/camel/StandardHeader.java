package com.zoli.camel;

import com.google.gson.Gson;


public class StandardHeader {

    public String interface_id;
    public String interface_step;


    public String getInterface_id() {
        return interface_id;
    }

    public void setInterface_id(String esb_interface_id) {
        this.interface_id = esb_interface_id;
    }

    public String getInterface_step() {
        return interface_step;
    }

    public void setInterface_step(String esb_interface_step) {
        this.interface_step = esb_interface_step;
    }



    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
