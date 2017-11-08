package com.trp.mifid.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.springframework.stereotype.Component;

@Component
public class MiFidCamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // On Demand Trigger
        from("direct:onDemandTrigger")
                .routeId("onDemandTriggerRoute")
                .routeDescription("This route is called to immediately invoke the MiFID II data generation process.")
                .log("onDemandTrigger: ${body}")
                .to("direct:executeProcess");

        // Scheduled Trigger
        from("direct:scheduledTrigger")
                .routeId("scheduledTriggerRoute")
                .log("scheduledTrigger: ${body}");


        // Run MiFID Generation Process
        from("direct:executeProcess")
                .routeId("executeProcessRoute")
                .log("executeProcess: ${body}")
                // header property defines whether if simulated mode
                .bean("SourceFileValidator", "validate")
                .to("direct:exportGeneratedFile");


        from("direct:exportGeneratedFile")
                .routeId("exportGeneratedFile")
                .log("exportGeneratedFile: ${body}");


    }
}
