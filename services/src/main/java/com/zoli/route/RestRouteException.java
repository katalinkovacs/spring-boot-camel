package com.zoli.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static sun.util.logging.LoggingSupport.log;

@Component
public class RestRouteException extends RouteBuilder {

    protected static final Logger LOG = LoggerFactory.getLogger(RestRouteException.class);

    @Override
    public void configure() throws Exception {

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

        from("jetty://http://0.0.0.0:8082/say")
                .transform(method("myBean", "saySomething"))

                .log("log ${body}")
                .log(LoggingLevel.INFO, LOG, "Info LOG ----------- ${body}")
                .log(LoggingLevel.ERROR, LOG, "Error LOG ------------ ${body}")
                .log(LoggingLevel.DEBUG, LOG, "Debug LOG  -----------------${body}");
    }
}
