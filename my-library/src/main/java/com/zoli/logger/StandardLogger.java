package com.zoli.logger;

import com.zoli.camel.StandardHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.Exchange;


import static com.zoli.route.constants.Constants.*;
import static jdk.nashorn.internal.objects.NativeMath.log;

public class StandardLogger {


    private static final Logger LOG = LoggerFactory.getLogger(StandardLogger.class);

    private String default_esb_interface_id;

    public String getDefault_esb_interface_id() {
        return default_esb_interface_id;
    }

    public void setDefault_esb_interface_id(String default_esb_interface_id) {
        this.default_esb_interface_id = default_esb_interface_id;
    }

    public String getDefault_esb_interface_step() {
        return default_esb_interface_step;
    }

    public void setDefault_esb_interface_step(String default_esb_interface_step) {
        this.default_esb_interface_step = default_esb_interface_step;
    }

    private String default_esb_interface_step;


    public StandardLogger() {
    }





    public void logStart(Exchange exchange) throws Exception {

        StandardHeader standardHeader = new StandardHeader();

        standardHeader.setEsb_interface_id(default_esb_interface_id);
        standardHeader.setEsb_interface_step(default_esb_interface_step);

        exchange.getIn().setHeader("standardHeader", standardHeader);
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



        StandardHeader standardHeader = (StandardHeader) exchange.getIn().getHeader("standardHeader");
        String standardheaderJson = standardHeader.toString();

        //for just to see how it works roughly
        LOG.info("StepName is: " +stepName + " The body of the message: " +  exchange.getIn().getBody().toString() + "The standard header : " + standardheaderJson);
        /*LOG.debug("DEBUG----------------");
        LOG.warn("WARN---------------");*/
    }


}