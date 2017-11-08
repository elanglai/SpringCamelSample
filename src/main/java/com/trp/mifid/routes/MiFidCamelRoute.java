package com.trp.mifid.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.ValueBuilder;
import org.springframework.stereotype.Component;
import static org.apache.camel.LoggingLevel.*;

@Component
public class MiFidCamelRoute extends RouteBuilder {

    private static final String OPERATION_MODE = "operationMode";

    @Override
    public void configure() throws Exception {

        // On Demand Trigger
        from("direct:onDemandTrigger")
                .routeId("onDemandTriggerRoute")
                .routeDescription("Immediately runs the MiFID II data generation process.")
                .log(INFO,"Process was manually triggered.")
                .to("direct:executeProcess");

        // Scheduled Trigger
        from("direct:scheduledTrigger")
                .routeId("scheduledTriggerRoute")
                .routeDescription("Runs the MiFID II data generation process according to a pre-defined schedule.")
                .log(INFO,"Process was automatically triggered.")
                .to("direct:executeProcess");

        // Main MiFID Generation Process
        from("direct:executeProcess")
                .routeId("executeProcessRoute")
                .routeDescription("Immediately runs the MiFID II data generation process.")
                .log("executeProcess: ${body}")
                .choice()
                    .when(header(OPERATION_MODE).isEqualTo("DryRun"))
                        .to("direct:loadSourceFiles")
                        .to("file://inbox?move=.done")
                        .bean("SourceFileValidator", "validate")
                    .otherwise()
                        .to("direct:loadSourceFiles")
                        .bean("SourceFileValidator", "validate")
                        .bean("MiFidGenerator", "generate")
                        .to("direct:exportGeneratedFile")
                .endChoice()
                .to("direct:exportGeneratedFile");

        // Load source files
        from("file:/ReportingAutomations/TEST/InputFiles/MIFIDII?noop=true")
                .routeId("loadSourceFiles")
                .routeDescription("Load the source files into the in-memory data model.")
                .unmarshal().csv()
                .bean("SourceFileImport", "importSource(${CamelFileName}, ${body})")
                .log(INFO, "loadedSourceFiles: ${CamelFileName}");

        // Generate the MiFID summary result
        from("direct:generateMiFID")
                .routeId("exportGeneratedFile")
                .routeDescription("Generated the MiFID II XML feed.")
                .log("exportGeneratedFile: ${body}");

        // Export the MiFID II XML feed
        from("direct:exportGeneratedFile")
                .routeId("exportGeneratedFile")
                .routeDescription("Generated the MiFID II XML feed.")
                .log("exportGeneratedFile: ${body}");
    }


}
