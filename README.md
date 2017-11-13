# MiFID II XML Feed Generator

## To build the mifid application
mvn clean package

## To enable testing or manual re-run of the MiFID generator, you have multiple options
a) Use POSTMAN to start/stop the camel-route "testrun-loadSourceFiles"
   POST http://{{server}}/camel/routes/testrun-loadSourceFiles/start

b) Launch the application with the run parameter enabled.
   ie. java -jar MifidGenerator-0.0.1-SNAPSHOT.jar -Dtrp.mifid.test.run.enabled=true

c) Use an external application.properties to override the application's default properties
   ie. file:    /config/application.properties
       content: trp.mifid.test.run.enabled=true

## To inspect the in-memory data model, you can run the H2 console 
a) Using your browser, go to the url 
   http:/{{server}}/h2-console
b) Login with the following settings

   | Property     | Value                |
   | ---          | ---                  |
   | Driver Class | org.h2.Driver        |
   | JDBC URL     | jdbc:h2:mem:mifid-db |  
   | User Name    | sa                   |
   | Password     |                      |
   