package com.zoli.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;




@Component
public class RestRouteException extends RouteBuilder {

    final static Logger logger = LoggerFactory.getLogger(RestRouteException.class);



    @Override
    public void configure() throws Exception {

        logger.info("Entering application.");



        getContext().setStreamCaching(true);

        /*onException(Throwable.class)
                .handled(true)
                .log("gotException")

                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("501"))
                .setHeader(Exchange.HTTP_RESPONSE_TEXT, simple("Not implemented"))
                .removeHeader(Exchange.EXCEPTION_CAUGHT)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("hello");

                    }
                })
        ;*/


        logger.info("Hey Katalin! - This an Info from HelloWorld!");
        logger.debug("Hey Katalin! - This a Debug from HelloWorld!");

        from("jetty://http://0.0.0.0:8082/say")
                .transform(method("myBean", "saySomething"))
                .log("This is just a simple log .......with a body ${body}")
                .log(LoggingLevel.DEBUG, "This is DEBUG log .......")
                .log(LoggingLevel.INFO,"This is INFO log .......")
                .log(LoggingLevel.ERROR,"This is ERROR log .......");



        logger.info("Exiting application.");

    }


}
