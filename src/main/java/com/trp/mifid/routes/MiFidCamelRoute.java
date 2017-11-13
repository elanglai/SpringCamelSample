package com.trp.mifid.routes;

import com.trp.mifid.config.AppConfig;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.ValueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static org.apache.camel.LoggingLevel.*;

@Component
public class MiFidCamelRoute extends RouteBuilder {

    private static final String OPERATION_MODE = "operationMode";

    @Autowired
    private AppConfig appConfig;

    @Override
    public void configure() throws Exception {

        // On Demand Trigger
        from("direct:onDemandTrigger")
                .routeId("onDemandTriggerRoute")
                .routeDescription("Immediately runs the MiFID II data generation process.")
                .log(INFO,"Process was manually triggered.")
                //.setHeader(OPERATION_MODE, "TestRun")
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
                        .bean("SourceFileValidatorImpl", "validate")
                    .when(header(OPERATION_MODE).isEqualTo("FinalRun"))
                        .to("direct:loadSourceFiles")
                        .bean("SourceFileValidatorImpl", "validate")
                        .bean("MiFidGeneratorImpl", "generate")
                        .to("direct:exportGeneratedFile")
                .end()
                .to("direct:exportGeneratedFile");

        // Test run - Load source files
        from("file:" + appConfig.getSourcePath())
                .routeId("testrun-loadSourceFiles")
                .routeDescription("(Test run) Load the source files into the in-memory data model.")
                .description("At startup, this route is NOT active.")
                .autoStartup(appConfig.isTestRunEnabled())
                .log(INFO, "Loading the source file ${header.CamelFileName}")
                .bean("SourceFileImportImpl", "importSource(${header.CamelFileName}, ${body})");

        // Dry run - Load source files
        from("file:" + appConfig.getSourcePath() + "?scheduler=spring&scheduler.cron=" + appConfig.getDryRunCron())
                .routeId("dryrun-loadSourceFiles")
                .routeDescription("(Dry run) Load the source files into the in-memory data model.")
                .log(INFO, "Loading the source file ${header.CamelFileName}")
                .bean("SourceFileImportImpl", "importSource(${header.CamelFileName}, ${body})");

        // Final run - Load source files
        from("file:" + appConfig.getSourcePath() + "?scheduler=spring&scheduler.cron=" + appConfig.getFinalRunCron())
                .routeId("finalrun-loadSourceFiles")
                .routeDescription("(Final run) Load the source files into the in-memory data model.")
                .log(INFO, "Loading the source file ${header.CamelFileName}")
                .bean("SourceFileImportImpl", "importSource(${header.CamelFileName}, ${body})");


        // If an file error is encountered, Camel's emdpoin, the exception is seet to direct:file-error
        from("direct:file-error")
                .routeId("fileErrorRoute")
                .log(ERROR, "File error detected");

        // Generate the MiFID summary result
        from("direct:generateMiFID")
                .routeId("generateMiFID")
                .routeDescription("Generated the MiFID II XML feed.")
                .log("generateMiFID: ${body}");

        // Export the MiFID II XML feed
        from("direct:exportGeneratedFile")
                .routeId("exportGeneratedFile")
                .routeDescription("Generated the MiFID II XML feed.")
                .log("exportGeneratedFile: ${body}");

    }


}
