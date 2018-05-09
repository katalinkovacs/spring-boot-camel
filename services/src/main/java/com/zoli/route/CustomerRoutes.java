package com.zoli.route;


import com.zoli.beans.Customer1;
import com.zoli.beans.Customer2;
import com.zoli.beans.TransformCustomerProcessor;
import com.zoli.logger.StandardLogger;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component // adds route to camelcontext
public class CustomerRoutes extends RouteBuilder {


    final static Logger logger = LoggerFactory.getLogger(RestRouteException.class);
    public StandardLogger stdLog = new StandardLogger();


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
        /*restConfiguration()
                .component("jetty")
                .scheme("http")
                .host("localhost")
                .port("8083")
                .contextPath("/services")
         ;*/

        //common rest config -- standard always like this -- like restcontroller in MVC
        /*rest()
            .get("/show") //http get request comes here and gets routed to direct:get-route -- this can be invoked from browser
            .route()
                .to("direct:get-route")
            .endRest()

            .post("/show") //http post request comes here and gets routed to direct:post-route -- you NEED TO USE POSTMAN FOR THIS!!!!!
            .route()
                .to("direct:post-route")
            .endRest()
        ;*/


        stdLog.setDefaultInterface_id("MY_Interface_id");
        stdLog.setDefaultInterface_step("MY_Interface_step");

        // the request from .post("/show") comes here
        //these are like your model and view in MVC
        /*from("direct:post-route")*/
        from("jetty://http://0.0.0.0:8083/postcustomer")  // --> camel creates exchange
                .bean(stdLog, "logStart")
                .bean(stdLog, "logFileTransformationStart")
                .unmarshal(customer1DataFormat)
                .bean(transformCustomerProcessor, "transformCustomer")
                .marshal(customer2DataFormat)
                .bean(stdLog, "logFileTransformationFinish")
                .log("${body}")
                .bean(stdLog, "logFinish");


        // the request from .get("/show") comes here -- this can be invoked from browser
        /*from("direct:get-route")*/
        from("jetty://http://0.0.0.0:8083/welcome")
                .transform(method("myBean", "welcome"))
                .setHeader(Exchange.CONTENT_TYPE, simple("text/plain"))
                .log("${body}");



    }



}
