package com.trp.mifid.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value="MiFidGenerator")
public class MiFidGenerator {

    final Logger logger = LoggerFactory.getLogger(MiFidGenerator.class);

    public void generate() {
        logger.trace("generate:start");
        try {
            logger.info("Msg 1");
        } finally {
            logger.trace("generate:end");
        }

    }
}
