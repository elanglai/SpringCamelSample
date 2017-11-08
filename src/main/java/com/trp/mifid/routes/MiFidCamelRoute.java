package com.trp.mifid.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.springframework.stereotype.Component;

@Component
public class MiFidCamelRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("direct:firstRoute")
                .log("Camel body: ${body}");
    }
}
