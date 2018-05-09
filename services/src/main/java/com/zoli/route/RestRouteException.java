package com.zoli.route;

import com.zoli.logger.StandardLogger;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.zoli.route.constants.Constants.SIMPLE_BODY;
import static com.zoli.route.constants.Constants.STEP_FINISH;
import static com.zoli.route.constants.Constants.STEP_START;


@Component
public class RestRouteException extends RouteBuilder {

    final static Logger logger = LoggerFactory.getLogger(RestRouteException.class);
    public StandardLogger stdLog = new StandardLogger();


    @Override
    public void configure() throws Exception {

        logger.info("-----------------Entering application----------------.");

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

        stdLog.setDefaultInterface_id("MY_Interface_id");
        stdLog.setDefaultInterface_step("MY_Interface_step");


        /*logger.info("-------------- This an INFO from RestRouteException-----------------");
        logger.debug("-------------- This an DEBUG from RestRouteException-----------------");*/

        from("jetty://http://0.0.0.0:8082/say")  // --> camel creates exchange
                /*.log(STEP_START)*/
                .bean(stdLog, "logStart") //bean invocation that logs
                .transform(method("myBean", "saySomething"))
                /*.log("This is just a simple log .......with a body ${body}")*/
                /*.log(SIMPLE_BODY)*/
                /*.log(LoggingLevel.DEBUG, "This is DEBUG log .......")
                .log(LoggingLevel.INFO,"This is INFO log .......")
                .log(LoggingLevel.ERROR,"This is ERROR log .......")*/
                /*.log(STEP_FINISH)*/
                    //bean ref + method
                .bean(stdLog, "logFinish");
                /*.log("log");*/




        logger.info("-------------------Exiting application.------------------");

    }


}
