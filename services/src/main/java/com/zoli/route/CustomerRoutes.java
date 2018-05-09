package com.zoli.route;


import com.zoli.beans.Customer1;
import com.zoli.beans.Customer2;
import com.zoli.beans.TransformCustomerProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component // adds route to camelcontext
public class CustomerRoutes extends RouteBuilder {

    @Autowired
    TransformCustomerProcessor transformCustomerProcessor;

    DataFormat customer1DataFormat = new JacksonDataFormat(Customer1.class);

    DataFormat customer2DataFormat = new JacksonDataFormat(Customer2.class);

    @Override
    public void configure() throws Exception {

        getContext().setStreamCaching(true);

        onException(Throwable.class)
                .handled(true)
                .log("gotException")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("501"))
                .setHeader(Exchange.HTTP_RESPONSE_TEXT, simple("Not implemented"))
                .removeHeader(Exchange.EXCEPTION_CAUGHT)
        ;


        //common jetty config -- standard always like this
        restConfiguration()
                .component("jetty")
                .scheme("http")
                .host("localhost")
                .port("8083")
                .contextPath("/services")
         ;

        //common rest config -- standard always like this -- like restcontroller in MVC
        rest()
            .get("/show") //http get request comes here and gets routed to direct:get-route -- this can be invoked from browser
            .route()
                .to("direct:get-route")
            .endRest()

            .post("/show") //http post request comes here and gets routed to direct:post-route -- you NEED TO USE POSTMAN FOR THIS!!!!!
            .route()
                .to("direct:post-route")
            .endRest()
        ;


        // the request from .post("/show") comes here
        //these are like your model and view in MVC
        from("direct:post-route")
                .unmarshal(customer1DataFormat)
                .bean(transformCustomerProcessor, "transformCustomer")
                .marshal(customer2DataFormat)
                .log("log ${body}");


        // the request from .get("/show") comes here -- this can be invoked from browser
        from("direct:get-route")
                .transform(method("myBean", "welcome"))
                .setHeader(Exchange.CONTENT_TYPE, simple("text/plain"))
                .log("log ${body}");



    }



}
