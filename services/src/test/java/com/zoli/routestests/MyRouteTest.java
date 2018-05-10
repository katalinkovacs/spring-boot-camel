package com.zoli.routestests;

import com.zoli.Application;
import org.apache.camel.CamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;


@ActiveProfiles("unittest")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes =  Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DisableJmx(true)
public class MyRouteTest extends CamelTestSupport {


    @Autowired
    private CamelContext camelContext;


    protected CamelContext createCamelContext() throws Exception { return camelContext; }




    @Ignore
    @Test
    public void insertRouteTest() throws Exception {



        camelContext.start();



        template.request(
                "direct:start",
                ex -> {
                    ex.getIn().setBody(null);
                });



        camelContext.stop();
    }



    @Test
    public void aggregateRouteTest() throws Exception {



        camelContext.start();



        template.request(
                "direct:start-aggregation",
                ex -> {
                    ex.getIn().setBody(null);
                });



        camelContext.stop();
    }



}
