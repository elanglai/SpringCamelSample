To enable testing or manual re-run of the MiFID generator, you have multiple options
a) Use POSTMAN to start/stop the camel-route "testrun-loadSourceFiles"
   POST http://{{host:port}}/camel/routes/testrun-loadSourceFiles/start

b) Launch the application with the property
   ie. java -jar MifidGenerator-0.0.1-SNAPSHOT.jar -Dtrp.mifid.test.run.enabled=true

c) Use an external application.properties to override the application's default properties
   ie. file:    /config/application.properties
       content: trp.mifid.test.run.enabled=true