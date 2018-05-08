package com.zoli.logger;

import com.zoli.camel.StandardHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.Exchange;
import static com.zoli.route.constants.Constants.*;


public class StandardLogger {

    private static final Logger LOG = LoggerFactory.getLogger(StandardLogger.class);

    private String defaultInterface_id;
    private String defaultInterface_step;


    public String getDefaultInterface_id() {
        return defaultInterface_id;
    }

    public void setDefaultInterface_id(String defaultInterface_id) {
        this.defaultInterface_id = defaultInterface_id;
    }

    public String getDefaultInterface_step() {
        return defaultInterface_step;
    }

    public void setDefaultInterface_step(String defaultInterface_step) {
        this.defaultInterface_step = defaultInterface_step;
    }

    public StandardLogger() {
    }





    public void logStart(Exchange exchange) throws Exception {

        StandardHeader standardHeader = new StandardHeader();

        standardHeader.setInterface_id(defaultInterface_id);
        standardHeader.setInterface_step(defaultInterface_step);

        exchange.getIn().setHeader("standardHeader", standardHeader);
        log(exchange, STEP_START);
    }




    public void logFinished(Exchange exchange) throws Exception {
        log(exchange, STEP_FINISH);
    }






    private void log(Exchange exchange, String stepName) throws Exception {

        StandardHeader standardHeader = (StandardHeader) exchange.getIn().getHeader("standardHeader");
        String standardheaderJson = standardHeader.toString();

        LOG.info("StepName is: " +stepName + " The body of the message: " +  exchange.getIn().getBody().toString() + " The standard header is: " + standardheaderJson);
        LOG.info("----------another info----------");

    }


}