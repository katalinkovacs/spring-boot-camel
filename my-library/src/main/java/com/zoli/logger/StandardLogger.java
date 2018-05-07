package com.zoli.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.Exchange;



import static com.zoli.route.constants.Constants.STEP_FINISH;
import static com.zoli.route.constants.Constants.STEP_START;
import static com.zoli.route.constants.Constants.STEP_START_PROCESS;
import static jdk.nashorn.internal.objects.NativeMath.log;

public class StandardLogger {


    private static final Logger LOG = LoggerFactory.getLogger(StandardLogger.class);

    private String defaultIntegrationId = "Use stdLog.setDefaultIntegrationId(\"int001\") in main camel routebuilder.configure!";
    private String defaultInterfaceId = "Use stdLog.setDefaultInterfaceId(\"inf0002\") in main camel routebuilder.configure!";


    public StandardLogger() {
    }





    public void logStart(Exchange exchange) throws Exception {
        log(exchange, STEP_START);
    }




    public void logFinished(Exchange exchange) throws Exception {
        log(exchange, STEP_FINISH);
    }





    /*private void log(Exchange exchange, String stepName) throws Exception {
        updateStandardHeader(exchange, this.defaultIntegrationId, this.defaultInterfaceId, stepName, defaultSourceSystem, overrideSourceSystem);
        createLogMessage(exchange);
    }

    private void log(Exchange exchange, String stepName, String stepSystem) throws InternalLogicException {
        if (stepName.equals(STEP_RECEIVE) && stepSystem != null && !stepSystem.isEmpty()) {
            this.overrideSourceSystem = stepSystem;
        }

    }*/








}