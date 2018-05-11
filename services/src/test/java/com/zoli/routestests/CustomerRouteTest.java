package com.zoli.routestests;


import com.zoli.Application;
import com.zoli.CustomerApplication;
import com.zoli.route.CustomerRoutes;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.apache.commons.io.FileUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ResourceUtils;

import java.nio.charset.StandardCharsets;




@ActiveProfiles("unittest")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = CustomerApplication.class)
@DisableJmx(true)
public class CustomerRouteTest extends CamelTestSupport {


    private static final Logger LOG = LoggerFactory.getLogger(CustomerRouteTest.class);


    private static String cust1 = "";
    private static String cust2 = "";

    private Exchange exchange = new DefaultExchange(context);


    @Autowired
    private CamelContext camelContext;


    protected CamelContext createCamelContext() throws Exception { return camelContext; }

    @Override
    public boolean isCreateCamelContextPerClass() {
        return false;
    }

    public void initiateTestData() throws Exception
    {
        cust1 = FileUtils.readFileToString(ResourceUtils.getFile("classpath:test-data/customer1.json"), StandardCharsets.UTF_8);
        cust2 = FileUtils.readFileToString(ResourceUtils.getFile("classpath:test-data/customer1.json"), StandardCharsets.UTF_8);
    }

    @Test
    public void route_whenValidInput_thenValidOutput() throws Exception{

        LOG.info("Starting test.................");

        initiateTestData();


        final RouteDefinition route = context.getRouteDefinition("route-postcustomer");
        route.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("direct:in");
                interceptSendToEndpoint("file:TransformationXmlToXml/src/main/resources/data/outbox")
                        .skipSendToOriginalEndpoint().to("mock:out");
            }
        });

      /*  final RouteDefinition route0052 = context.getRouteDefinition(ROUTE_ID_SENDTO_TA_NAV_0052);
        route0052.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("http4://get-inf0052-ta-nav*")
                        .skipSendToOriginalEndpoint()
                        .setBody().constant(cust1)
                        .to("mock:0052endpoint");
            }
        });*/


        context.start();

        MockEndpoint mockDirectVm = getMockEndpoint("mock:out");
        mockDirectVm.expectedMessageCount(1);

        //Send for Success Test
     /*   exchange = template.request("direct:main-start", ex -> {
            ex.getIn().setHeader("entityIDlist","123,456,789");
        });*/

        assertMockEndpointsSatisfied();

        // Assess the value
        JSONAssert.assertEquals(cust2, exchange.getIn().getBody().toString(), false);

        context.stop();
    }





}
