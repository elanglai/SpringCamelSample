package com.trp.mifid.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value="MiFidGenerator")
public class MiFidGenerator {

    final Logger logger = LoggerFactory.getLogger(MiFidGenerator.class);

    public void validate() {
        logger.trace("validate:start");
        try {
            //TODO: traverse source files

            //TODO: identify missing source files

            //TODO: read-in source files and validate content

            //TODO: on errors, throw SourceFileException
            logger.info("Msg 1");
            logger.warn("Msg 2");
            logger.error("Msg #3");
            logger.debug("Msg #4");

        } finally {
            logger.trace("validate:end");
        }

    }
}
