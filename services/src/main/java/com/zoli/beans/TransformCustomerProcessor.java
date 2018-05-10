package com.zoli.beans;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;


@Component
public class TransformCustomerProcessor {


    public void transformCustomer(Exchange exchange){

        Customer1 customer1 = (Customer1) exchange.getIn().getBody();

        Customer2 customer2 = new Customer2();

        customer2.setLastName(customer1.getFamilyName());
        customer2.setFirstName(customer1.getGivenName());

        exchange.getIn().setBody(customer2);

        /*try {
            throw new NullPointerException();
        }
        catch (NullPointerException e) {
            System.out.println(e + "Catch ----------- NullPointerException------------");
        }*/

    }


}
