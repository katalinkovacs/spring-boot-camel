package com.zoli.camel;

import com.google.gson.Gson;


public class StandardHeader {

    public String esb_interface_id;
    public String esb_interface_step;


    public String getEsb_interface_id() {
        return esb_interface_id;
    }

    public void setEsb_interface_id(String esb_interface_id) {
        this.esb_interface_id = esb_interface_id;
    }

    public String getEsb_interface_step() {
        return esb_interface_step;
    }

    public void setEsb_interface_step(String esb_interface_step) {
        this.esb_interface_step = esb_interface_step;
    }



    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
