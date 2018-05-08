package com.zoli.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.Exchange;


import static com.zoli.route.constants.Constants.*;
import static jdk.nashorn.internal.objects.NativeMath.log;

public class StandardLogger {


    private static final Logger LOG = LoggerFactory.getLogger(StandardLogger.class);


    public StandardLogger() {
    }





    public void logStart(Exchange exchange) throws Exception {
        log(exchange, STEP_START);
    }




    public void logFinished(Exchange exchange) throws Exception {
        log(exchange, STEP_FINISH);
    }

    public void logKati(Exchange exchange) throws Exception {
        log(exchange, STEP_KATI);
    }




    private void log(Exchange exchange, String stepName) throws Exception {
        // updateStandardHeader(exchange, this.defaultIntegrationId, this.defaultInterfaceId, stepName, defaultSourceSystem, overrideSourceSystem);
        //createLogMessage(exchange);


        //for just to see how it works roughly
        LOG.info("StepName is: " +stepName + " The body of the message: " +  exchange.getIn().getBody().toString());
        /*LOG.debug("DEBUG----------------");
        LOG.warn("WARN---------------");*/
    }


}